package com.hartcircle.admin.services;

import com.hartcircle.admin.dto.JwtRequest;
import com.hartcircle.admin.dto.JwtResponse;
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

    private final RestTemplate restTemplate;

    @Value("${user.service.url}")  // from application.properties
    private String userServiceUrl;

    @Value("${jwt.service.url}")
    private String jwtServiceUrl;

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

    public String generateToken(String nic) {
        try {
            JwtRequest request = new JwtRequest();
            request.setUserNIC(nic);

            ResponseEntity<JwtResponse> response = restTemplate.postForEntity(
                    jwtServiceUrl + "/jwt/generate",
                    request,
                    JwtResponse.class
            );

            return response.getBody() != null ? response.getBody().getToken() : null;
        } catch (Exception e) {
            System.out.println("Token generation failed: " + e.getMessage());
            return null;
        }
    }

}
