package com.ProblemSolving.Platform.Service.Interfaces;

import com.ProblemSolving.Platform.DTO.CodingProblemDTO;
import com.ProblemSolving.Platform.Entities.CodingProblem;

import java.util.List;
import java.util.UUID;

public interface ProblemService {

    List<CodingProblemDTO> getAllProblems();
    CodingProblemDTO getProblemById(UUID id);
    CodingProblemDTO createProblem(CodingProblemDTO codingProblemDTO);
    CodingProblemDTO updateProblem(UUID id, CodingProblemDTO codingProblemDTO);
    void deleteProblem(UUID id);
}
