package com.ProblemSolving.Platform.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CodingProblemDTO {

    private UUID id;
    private String title;
    private String description;
    private int difficultyLevel;
    private int timeLimit;
    private int memoryLimit;
    private String timeToSolve;
    private String snippetPYTHON;
    private String snippetCPP;
    private String snippetJAVA;
}
