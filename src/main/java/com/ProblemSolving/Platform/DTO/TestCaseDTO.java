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
public class TestCaseDTO {
    private UUID id;
    private String input;
    private String expectedOutput;
    private CodingProblemDTO codingProblem;

}
