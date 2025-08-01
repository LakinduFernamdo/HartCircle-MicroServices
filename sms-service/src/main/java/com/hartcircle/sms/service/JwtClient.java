package com.hartcircle.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class JwtClient {

    @Autowired
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
