package com.ProblemSolving.Platform.Repository;


import com.ProblemSolving.Platform.Entities.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, UUID> {

}
