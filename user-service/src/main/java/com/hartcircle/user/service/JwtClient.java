package com.hartcircle.user.service;

import com.hartcircle.jwt.Dto.JwtRequest;
import com.hartcircle.jwt.Dto.JwtResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JwtClient {

    @Value("${jwt.service.url}")
    private String jwtServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String generateToken(String userNIC) {
        JwtRequest request = new JwtRequest();
        request.setUserNIC(userNIC);

        JwtResponse response = restTemplate.postForObject(
                jwtServiceUrl + "/jwt/generate",
                request,
                JwtResponse.class
        );

        return response.getToken();
    }

    public boolean validateToken(String token, String userNIC) {
        JwtRequest request = new JwtRequest();
        request.setToken(token);
        request.setUserNIC(userNIC);

        Boolean isValid = restTemplate.postForObject(
                jwtServiceUrl + "/jwt/validate",
                request,
                Boolean.class
        );

        return Boolean.TRUE.equals(isValid);
    }

    public String extractUserNIC(String token) {
        JwtRequest request = new JwtRequest();
        request.setToken(token);

        return restTemplate.postForObject(
                jwtServiceUrl + "/jwt/extract",
                request,
                String.class
        );
    }
}
