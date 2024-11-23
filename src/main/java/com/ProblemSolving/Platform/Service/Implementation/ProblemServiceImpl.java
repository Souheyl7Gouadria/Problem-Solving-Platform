package com.ProblemSolving.Platform.Service.Implementation;

import com.ProblemSolving.Platform.DTO.CodingProblemDTO;
import com.ProblemSolving.Platform.Entities.CodingProblem;
import com.ProblemSolving.Platform.Repository.ProblemRepository;
import com.ProblemSolving.Platform.Service.Mappers.ProblemMapper;
import com.ProblemSolving.Platform.Service.Interfaces.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;
    private final ProblemMapper problemMapper;

    @Override
    public List<CodingProblemDTO> getAllProblems() {
        return problemRepository.findAll().stream()
                .map(problemMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CodingProblemDTO getProblemById(UUID id) {
        return problemRepository.findById(id)
            .map(problemMapper::toDTO)
            .orElse(null);
}


    @Override
    public CodingProblemDTO createProblem(CodingProblemDTO codingProblemDTO) {
        CodingProblem codingProblem = problemMapper.INSTANCE.toEntity(codingProblemDTO);
        CodingProblem savedProblem = problemRepository.save(codingProblem);
        return problemMapper.INSTANCE.toDTO(savedProblem);
    }


    @Override
    public CodingProblemDTO updateProblem(UUID id, CodingProblemDTO codingProblemDTO) {
        CodingProblem existingProblem = problemRepository.findById(id).orElse(null);
        if (existingProblem != null) {
            problemMapper.INSTANCE.updateFromDto(codingProblemDTO, existingProblem);
            return problemMapper.INSTANCE.toDTO(problemRepository.save(existingProblem));
        }
        return null;
    }
    @Override
    public void deleteProblem(UUID id) {
        problemRepository.deleteById(id);
    }

}
