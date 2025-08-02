package com.hartcircle.bid.service;


//check it post exist on Database

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostClient {

    @Value("${post.service.url}")
    private String postServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    public boolean postExist(Integer postID) {
        try {
            String url = postServiceUrl + "/api/v1/post/" + postID;
            ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);
            return response.getBody() != null && response.getBody();
        } catch (Exception e) {
            return false;
        }
    }
    public String getPostOwnerNic(Integer postID) {
        try {
            String url = postServiceUrl + "/api/v1/post/" + postID + "/owner";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Unable to fetch post owner for post ID: " + postID);
        }
    }
}
