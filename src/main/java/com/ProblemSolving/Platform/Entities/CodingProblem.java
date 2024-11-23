package com.ProblemSolving.Platform.Entities;

import com.ProblemSolving.Platform.Service.Mappers.DurationAttributeConverter;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CodingProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private int difficultyLevel;
    private int timeLimit;
    private int memoryLimit;
    @Convert(converter = DurationAttributeConverter.class)
    private Duration timeToSolve;
    @Column(length = 10000)
    private String snippetPYTHON;

    @Column(length = 10000)
    private String snippetCPP;

    @Column(length = 10000)
    private String snippetJAVA;
    @OneToMany(mappedBy = "codingProblem", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TestCase> testCases;

    @OneToMany(mappedBy = "codingProblem", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Submission> submissions;

}
