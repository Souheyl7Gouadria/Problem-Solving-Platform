package com.ProblemSolving.Platform.Controller;

import com.ProblemSolving.Platform.DTO.CodingProblemDTO;
import com.ProblemSolving.Platform.Service.Interfaces.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/problems")
public class ProblemController {

    private final ProblemService problemService;

    @GetMapping
    public ResponseEntity<List<CodingProblemDTO>> getAllProblems() {
        List<CodingProblemDTO> problems = problemService.getAllProblems();
        return ResponseEntity.ok().body(problems);
    }
@GetMapping("/{id}")
    public ResponseEntity<CodingProblemDTO> getProblemById(@PathVariable("id") UUID id) {
        CodingProblemDTO codingProblemDTO = problemService.getProblemById(id);
        if(codingProblemDTO != null){
            return ResponseEntity.ok().body(codingProblemDTO);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CodingProblemDTO> createProblem(@RequestBody CodingProblemDTO codingProblemDTO){
        CodingProblemDTO createdProblem = problemService.createProblem(codingProblemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProblem);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CodingProblemDTO> updateProblem(@PathVariable("id") UUID id, @RequestBody CodingProblemDTO codingProblemDTO){
        CodingProblemDTO updatedProblem = problemService.updateProblem(id, codingProblemDTO);
        if (updatedProblem != null) {
            return ResponseEntity.ok(updatedProblem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProblem(@PathVariable("id") UUID id){
        problemService.deleteProblem(id);
        return ResponseEntity.noContent().build();
    }

}
