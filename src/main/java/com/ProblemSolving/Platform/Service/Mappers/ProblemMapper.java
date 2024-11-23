package com.ProblemSolving.Platform.Service.Mappers;

import com.ProblemSolving.Platform.DTO.CodingProblemDTO;
import com.ProblemSolving.Platform.Entities.CodingProblem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.time.Duration;


@Mapper(componentModel = "spring")
public interface ProblemMapper {
    ProblemMapper INSTANCE = Mappers.getMapper(ProblemMapper.class);

    @Mapping(target = "timeToSolve", expression = "java(parseDuration(codingProblemDTO.getTimeToSolve()))")
    CodingProblem toEntity(CodingProblemDTO codingProblemDTO);

    @Mapping(target = "timeToSolve", expression = "java(formatDuration(codingProblem.getTimeToSolve()))")
    CodingProblemDTO toDTO(CodingProblem codingProblem);

    // this is for the update , because INSTANCE.toEntity , creates a new entity instance
    @Mapping(target = "id", ignore = true) 
    void updateFromDto(CodingProblemDTO codingProblemDTO, @MappingTarget CodingProblem codingProblem);

    default Duration parseDuration(String duration) {
        return duration == null ? null : Duration.parse(duration);
    }

    default String formatDuration(Duration duration) {
        return duration == null ? null : duration.toString();
    }
}


