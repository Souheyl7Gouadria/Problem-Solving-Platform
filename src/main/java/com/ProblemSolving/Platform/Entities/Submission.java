package com.ProblemSolving.Platform.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(columnDefinition = "TEXT")
    private String code;
    private Integer languageId;
    private int memoryUsed;
    private int timeTaken;
    private int totalTestCases;
    private int passedTestCases;
    private int points;

    @ManyToOne
    @JoinColumn(name = "coding_problem_id", nullable = false)
    @JsonBackReference
    private CodingProblem codingProblem;

}
