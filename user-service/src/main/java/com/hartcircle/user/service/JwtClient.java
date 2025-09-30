package com.hartcircle.user.service;

import com.hartcircle.user.dto.JwtRequest;
import com.hartcircle.user.dto.JwtResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
//Generate JWT tokens + Validate+ Extract userNIC

@Service
public class JwtClient {

    @Value("${jwt.service.url}")
    private String jwtServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    public String extractNic(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getTempSigningKey()) // Only used to extract NIC from the token body
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validateToken(String token, String nic) {
        try {
            JwtRequest request = new JwtRequest();
            request.setToken(token);
            request.setUserNIC(nic);

            ResponseEntity<Boolean> response = restTemplate.postForEntity(
                    jwtServiceUrl + "/jwt/validate",
                    request,
                    Boolean.class
            );

            return response.getBody() != null && response.getBody();
        } catch (Exception e) {
            return false;
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

    // Temporary key just to extract NIC from token locally
    @Value("${jwt.secret}")
    private String secret;

    private Key getTempSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
    //logout(listen JwtController class)
    public boolean logout(String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<Void> request = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    jwtServiceUrl + "/jwt/auth/logout",
                    HttpMethod.POST,
                    request,
                    String.class
            );

            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            System.out.println("Logout failed: " + e.getMessage());
            return false;
        }
    }

}

