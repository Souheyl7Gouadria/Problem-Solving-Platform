package com.ProblemSolving.Platform.Service.Mappers;

import com.ProblemSolving.Platform.DTO.TestCaseDTO;
import com.ProblemSolving.Platform.Entities.TestCase;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface TestCaseMapper {
    TestCaseMapper  INSTANCE = Mappers.getMapper(TestCaseMapper.class);

    TestCaseDTO toDTO(TestCase testCase);

    TestCase toEntity(TestCaseDTO testCaseDTO);
}