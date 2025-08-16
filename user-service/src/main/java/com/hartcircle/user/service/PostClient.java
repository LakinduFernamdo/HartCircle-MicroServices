package com.hartcircle.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service

public class PostClient {

    @Value("${post.service.url}")
    private String postServiceUrl;

    @Autowired
    private RestTemplate restTemplate;


    public String getUserNIC(Integer postID, String authHeader) {
        try {
            String url = postServiceUrl + "/api/v1/post/" + postID + "/owner";

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authHeader);

            HttpEntity<?> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Unable to fetch post owner for post ID: " + postID, e);
        }
    }


}
