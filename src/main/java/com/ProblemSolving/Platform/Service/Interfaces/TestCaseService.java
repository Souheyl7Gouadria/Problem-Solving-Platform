package com.ProblemSolving.Platform.Service.Interfaces;

import com.ProblemSolving.Platform.DTO.TestCaseDTO;

import java.util.List;
import java.util.UUID;

public interface TestCaseService {

    List<TestCaseDTO> getAllTestCases();
    TestCaseDTO getTestCaseById(UUID id);
    TestCaseDTO createTestCase(TestCaseDTO testCaseDTO);
    List<TestCaseDTO> getTestCasesByProblemId(UUID id);
    void deleteTestCase(UUID id);
}
