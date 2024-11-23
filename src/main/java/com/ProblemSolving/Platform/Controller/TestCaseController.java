package com.ProblemSolving.Platform.Controller;


import com.ProblemSolving.Platform.DTO.TestCaseDTO;
import com.ProblemSolving.Platform.Service.Interfaces.TestCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/testCases")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class TestCaseController {

    private final TestCaseService testCaseService;

    @GetMapping
    public ResponseEntity<List<TestCaseDTO>> getAllTestCases() {
        List<TestCaseDTO> testCases = testCaseService.getAllTestCases();
        return ResponseEntity.ok().body(testCases);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestCaseDTO> getTestCaseById(@PathVariable("id") UUID id) {
        TestCaseDTO testCaseDTO = testCaseService.getTestCaseById(id);
        if(testCaseDTO != null){
            return ResponseEntity.ok().body(testCaseDTO);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TestCaseDTO> createTestCase(@RequestBody TestCaseDTO testCaseDTO) {
        TestCaseDTO createdTestCase = testCaseService.createTestCase(testCaseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTestCase);
    }

    @GetMapping("/problem/{id}")
    public ResponseEntity<List<TestCaseDTO>> getTestCasesByProblemId(@PathVariable UUID id){
        List<TestCaseDTO> testCases = testCaseService.getTestCasesByProblemId(id);
        return ResponseEntity.ok().body(testCases);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestCase(@PathVariable("id") UUID id) {
        testCaseService.deleteTestCase(id);
        return ResponseEntity.noContent().build();
    }
}
