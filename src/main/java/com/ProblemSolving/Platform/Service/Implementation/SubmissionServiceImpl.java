package com.ProblemSolving.Platform.Service.Implementation;

import com.ProblemSolving.Platform.Config.Constants;
import com.ProblemSolving.Platform.DTO.CompileRequest;
import com.ProblemSolving.Platform.DTO.CompileResponse;
import com.ProblemSolving.Platform.DTO.ProgrammingLang;
import com.ProblemSolving.Platform.DTO.SubmissionDTO;
import com.ProblemSolving.Platform.Entities.CodingProblem;
import com.ProblemSolving.Platform.Entities.Submission;
import com.ProblemSolving.Platform.Entities.TestCase;
import com.ProblemSolving.Platform.Repository.SubmissionRepository;
import com.ProblemSolving.Platform.Repository.ProblemRepository;
import com.ProblemSolving.Platform.Service.Mappers.SubmissionMapper;
import com.ProblemSolving.Platform.Service.Interfaces.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {

    @Value(Constants.COMPILER_BASE_URL)
    private String compilerUrl;

    private static final Logger logger = LoggerFactory.getLogger(SubmissionService.class);

    private final SubmissionRepository submissionRepository;
    private final ProblemRepository problemRepository;
    private final SubmissionMapper submissionMapper;
    private final LanguageService languageService;
    private final RestTemplate restTemplate;


    @Override
    public List<SubmissionDTO> getAllSubmissions() {
        return submissionRepository.findAll().stream()
                .map(submissionMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public SubmissionDTO getSubmissionById(UUID id) {
        return submissionRepository.findById(id)
                .map(submissionMapper::toDTO)
                .orElse(null);
    }

    @Override
    public SubmissionDTO createSubmission(SubmissionDTO submissionDTO) {
        CodingProblem codingProblem = problemRepository.findById(submissionDTO.getCodingProblem().getId()).orElse(null);

        if (codingProblem != null) {
            ProgrammingLang progLang = languageService.getProgrammingLang(submissionDTO.getLanguageId());
            List<TestCase> testCases = codingProblem.getTestCases().stream()
                    .map(testCase -> TestCase.builder()
                            .id(testCase.getId())
                            .input(testCase.getInput())
                            .expectedOutput(testCase.getExpectedOutput())
                            .build())
                    .collect(Collectors.toList());

            // Check if only one test case should be executed (for compilation)
            if (submissionDTO.getTotalTestCases() == 1) {
                testCases = Collections.singletonList(testCases.getFirst()); // Use only the first test case
                return compileOnly(submissionDTO, progLang, testCases);
            }

            // Save the submission only if it's not a compilation request
            Submission submission = submissionMapper.toEntity(submissionDTO);
            submission.setCodingProblem(codingProblem);
            Submission savedSubmission = submissionRepository.save(submission);
            return processAndSaveSubmission(savedSubmission, progLang, testCases);
        } else {
            throw new RuntimeException("Coding problem not found");
        }
    }

    private SubmissionDTO compileOnly(SubmissionDTO submissionDTO, ProgrammingLang progLang, List<TestCase> testCases) {
        CompileRequest compileRequest = CompileRequest.builder()
                .source_code(submissionDTO.getCode())
                .language(progLang)
                .test_cases(testCases)
                .build();

        CompileResponse[] compileResponses;

        try {
            compileResponses = restTemplate.postForObject(
                    compilerUrl + Constants.COMPILE_ENDPOINT,
                    compileRequest,
                    CompileResponse[].class
            );
        } catch (Exception e) {
            throw new RuntimeException("Compilation request failed");
        }

        // Create a temporary SubmissionDTO to return the compile response
        assert compileResponses != null;
        return SubmissionDTO.builder()
//                .code(submissionDTO.getCode())
//                .languageId(submissionDTO.getLanguageId())
                .codingProblem(submissionDTO.getCodingProblem())
                .totalTestCases(1)
                .passedTestCases(compileResponses[0].isSuccess() ? 1 : 0)
//                .memoryUsed(compileResponses[0].getMemory() / 1024)
//                .timeTaken((int)Double.parseDouble(compileResponses[0].getTime()) * 1000)
                .build();
    }

    private SubmissionDTO processAndSaveSubmission(Submission savedSubmission, ProgrammingLang progLang, List<TestCase> testCases) {
        CompileRequest compileRequest = CompileRequest.builder()
                .source_code(savedSubmission.getCode())
                .language(progLang)
                .test_cases(testCases)
                .build();

        CompileResponse[] compileResponses;
        logger.info("Sending compile request for {} : {}", compilerUrl + Constants.COMPILE_ENDPOINT, compileRequest);
        try {
            compileResponses = restTemplate.postForObject(
                    compilerUrl + Constants.COMPILE_ENDPOINT,
                    compileRequest,
                    CompileResponse[].class
            );
            logger.info("Received compile responses: {}", (Object) compileResponses);
        } catch (Exception e) {
            logger.error("Error during compilation request: {}", e.getMessage());
            throw new RuntimeException("Compilation request failed");
        }

        if (compileResponses != null) {
            int totalTestCases = compileResponses.length;
            int passedTestCases = 0;
            int totalMemoryUsed = 0;
            int totalTimeTaken = 0;
            double timeTaken = 0;

            for (CompileResponse compileResponse : compileResponses) {
                if (compileResponse.isSuccess()) {
                    passedTestCases++;
                }
                totalMemoryUsed += compileResponse.getMemory();
                timeTaken += Double.parseDouble(compileResponse.getTime());
            }
            totalTimeTaken = (int) (timeTaken * 1000);

            savedSubmission.setMemoryUsed(totalMemoryUsed / 1024);
            savedSubmission.setTimeTaken(totalTimeTaken / 1000);
            savedSubmission.setPassedTestCases(passedTestCases);
            savedSubmission.setTotalTestCases(totalTestCases);

            int points = (passedTestCases * 100 / totalTestCases);
            savedSubmission.setPoints(points);
            submissionRepository.save(savedSubmission);
        }
        return submissionMapper.toDTO(savedSubmission);
    }


    @Override
    public List<SubmissionDTO> getSubmissionsByProblemId(UUID id) {
        CodingProblem problem = problemRepository.findById(id).orElse(null);
        if (problem != null) {
            return problem.getSubmissions().stream()
                    .map(submissionMapper::toDTO)
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("No Problem with that Id");
        }
    }
}
