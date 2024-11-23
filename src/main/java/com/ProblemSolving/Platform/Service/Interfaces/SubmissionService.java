package com.ProblemSolving.Platform.Service.Interfaces;


import com.ProblemSolving.Platform.DTO.SubmissionDTO;
import java.util.List;
import java.util.UUID;

public interface SubmissionService {

    List<SubmissionDTO> getAllSubmissions();
    SubmissionDTO getSubmissionById(UUID id);
    SubmissionDTO createSubmission(SubmissionDTO submissionDTO);
    List<SubmissionDTO> getSubmissionsByProblemId(UUID id);
}
