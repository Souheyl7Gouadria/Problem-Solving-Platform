package com.ProblemSolving.Platform.Repository;

import com.ProblemSolving.Platform.Entities.CodingProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ProblemRepository extends JpaRepository<CodingProblem, UUID> {
}
