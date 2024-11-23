package com.ProblemSolving.Platform.Controller;

import com.ProblemSolving.Platform.DTO.SubmissionDTO;
import com.ProblemSolving.Platform.Service.Interfaces.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/submissions")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    @GetMapping
    public ResponseEntity<List<SubmissionDTO>> getAllSubmissions() {
        List<SubmissionDTO> submissionDTO = submissionService.getAllSubmissions();
        return ResponseEntity.ok().body(submissionDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubmissionDTO> getSubmissionById(@PathVariable("id") UUID id) {
        SubmissionDTO submissionDTO = submissionService.getSubmissionById(id);
        if(submissionDTO != null){
            return ResponseEntity.ok().body(submissionDTO);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("problem/{id}")
    public ResponseEntity<List<SubmissionDTO>> getSubmissionsByProblemId(@PathVariable("id") UUID id){
        List<SubmissionDTO> submissions = submissionService.getSubmissionsByProblemId(id);
        return ResponseEntity.ok().body(submissions);
    }

    @PostMapping
    public ResponseEntity<SubmissionDTO> createSubmission(@RequestBody SubmissionDTO submissionDTO) {
        SubmissionDTO createdSubmission = submissionService.createSubmission(submissionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubmission);
    }
}
