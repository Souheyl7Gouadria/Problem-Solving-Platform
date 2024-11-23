package com.ProblemSolving.Platform.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//
//        // Allow requests from Angular frontend
//        config.addAllowedOrigin("http://localhost:4200");
//        config.addAllowedMethod("*"); // Allow all methods (GET, POST, etc.)
//        config.addAllowedHeader("*"); // Allow all headers
//        config.setAllowCredentials(true); // Allow credentials
//
//        // Register the CORS configuration for all paths
//        source.registerCorsConfiguration("/**", config);
//
//        return new CorsFilter(source);
//    }
}
