package com.ProblemSolving.Platform.Service.Implementation;

import com.ProblemSolving.Platform.Config.Constants;
import com.ProblemSolving.Platform.DTO.ProgrammingLang;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class LanguageService {
    private final RestTemplate restTemplate;

    @Value(Constants.COMPILER_BASE_URL)
    private String compilerURl;

    public ProgrammingLang getProgrammingLang(Integer langId) {
        return restTemplate.getForObject(
                compilerURl + Constants.LANGUAGES_ENDPOINT + langId,
                ProgrammingLang.class
        );
    }
}