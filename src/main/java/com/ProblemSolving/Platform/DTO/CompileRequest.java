package com.ProblemSolving.Platform.DTO;


import com.ProblemSolving.Platform.Entities.TestCase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CompileRequest {

    private String source_code;
    private ProgrammingLang language;
    private List<TestCase> test_cases;
}
