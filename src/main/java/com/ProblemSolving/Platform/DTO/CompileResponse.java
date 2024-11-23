package com.ProblemSolving.Platform.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor

public class CompileResponse {

    private UUID testCaseId;
    private String output;
    private boolean success;
    private String message;
    private String time;
    private int memory;
    private String status_code_description;
}