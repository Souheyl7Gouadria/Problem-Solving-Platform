package com.ProblemSolving.Platform.Service.Mappers;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Duration;

@Converter(autoApply = true)
public class DurationAttributeConverter implements AttributeConverter<Duration, String> {

    @Override
    public String convertToDatabaseColumn(Duration attribute) {
        return attribute == null ? null : attribute.toString();
    }

    @Override
    public Duration convertToEntityAttribute(String dbData) {
        return dbData == null ? null : Duration.parse(dbData);
    }
}
