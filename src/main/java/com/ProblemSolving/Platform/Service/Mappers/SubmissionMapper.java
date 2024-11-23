package com.ProblemSolving.Platform.Service.Mappers;

import com.ProblemSolving.Platform.DTO.SubmissionDTO;
import com.ProblemSolving.Platform.Entities.Submission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface SubmissionMapper {

    SubmissionMapper INSTANCE = Mappers.getMapper(SubmissionMapper.class);

    SubmissionDTO toDTO(Submission submission);

    Submission toEntity(SubmissionDTO submissionDTO);
}