package com.ProblemSolving.Platform.Service.Implementation;


import com.ProblemSolving.Platform.DTO.CodingProblemDTO;
import com.ProblemSolving.Platform.DTO.TestCaseDTO;
import com.ProblemSolving.Platform.Entities.CodingProblem;
import com.ProblemSolving.Platform.Entities.TestCase;
import com.ProblemSolving.Platform.Repository.ProblemRepository;
import com.ProblemSolving.Platform.Repository.TestCaseRepository;
import com.ProblemSolving.Platform.Service.Mappers.ProblemMapper;
import com.ProblemSolving.Platform.Service.Mappers.TestCaseMapper;
import com.ProblemSolving.Platform.Service.Interfaces.TestCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestCaseServiceImpl implements TestCaseService {

    private final TestCaseRepository testCaseRepository;
    private final ProblemRepository problemRepository;
    private final TestCaseMapper testCaseMapper;
    private final ProblemMapper problemMapper;


    @Override
    public List<TestCaseDTO> getAllTestCases() {
        return testCaseRepository.findAll().stream()
                .map(testCaseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TestCaseDTO getTestCaseById(UUID id) {
        return testCaseRepository.findById(id)
                .map(testCaseMapper::toDTO)
                .orElse(null);
    }

    @Override
    public TestCaseDTO createTestCase(TestCaseDTO testCaseDTO) {
        CodingProblemDTO codingProblemDTO = testCaseDTO.getCodingProblem();

        TestCase testCase = testCaseMapper.INSTANCE.toEntity(testCaseDTO);
        CodingProblem codingProblem = problemMapper.INSTANCE.toEntity(codingProblemDTO);
        testCase.setCodingProblem(codingProblem);

        TestCase savedTestCase = testCaseRepository.save(testCase);
        return testCaseMapper.INSTANCE.toDTO(savedTestCase);
    }

    @Override
    public List<TestCaseDTO> getTestCasesByProblemId(UUID id){
        CodingProblem codingProblem = problemRepository.findById(id).orElse(null);
        if(codingProblem != null){
            return codingProblem.getTestCases().stream()
                    .map(testCaseMapper::toDTO)
                    .collect(Collectors.toList());
        }else{
            throw new RuntimeException("problem not found");
        }
    }

    @Override
    public void deleteTestCase(UUID id) {
        testCaseRepository.deleteById(id);
    }

}
