package com.hartcircle.post.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import org.springframework.http.*;

//Talks to user-service to check if a token is valid.Call this endpoint.http://localhost:8080/auth/validate
//This helps JwtAuthenticationFilter decide if the request is real or fake.

@Service
public class JwtClient {

    private final RestTemplate restTemplate;

    @Value("${user.service.url}")  // from application.properties
    private String userServiceUrl;

    public JwtClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String validateTokenAndGetNic(String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    userServiceUrl + "/auth/validate",
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            return response.getBody();
        } catch (HttpClientErrorException e) {
            return null;
        }

    }
}

