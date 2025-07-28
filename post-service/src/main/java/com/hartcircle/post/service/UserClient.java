package com.hartcircle.post.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserClient {

    @Value("${user.service.url}")
    private String userServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    public boolean userExists(String nic) {
        try {
            String url = userServiceUrl + "/api/v1/user/" + nic;
            ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);
            return response.getBody() != null && response.getBody();
        } catch (Exception e) {
            return false;
        }
    }
}
