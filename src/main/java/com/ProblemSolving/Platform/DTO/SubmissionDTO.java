package com.ProblemSolving.Platform.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionDTO {

    private UUID id;
    private String code;
    private Integer languageId;
    private int memoryUsed;
    private int timeTaken;
    private int totalTestCases;
    private int passedTestCases;
    private int points;
    private CodingProblemDTO codingProblem;
}
