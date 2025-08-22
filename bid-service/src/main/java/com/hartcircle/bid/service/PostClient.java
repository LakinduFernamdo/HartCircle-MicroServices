package com.hartcircle.bid.service;


//check it post exist on Database

import com.hartcircle.bid.dto.PostViewDto;
import com.hartcircle.bid.dto.Post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PostClient {

    @Value("${post.service.url}")
    private String postServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(PostClient.class);


    public boolean postExist(Integer postID, String authHeader) {
        try {
            String url = postServiceUrl + "/api/v1/post/" + postID;

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authHeader);

            HttpEntity<?> entity = new HttpEntity<>(headers);

            ResponseEntity<Boolean> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    Boolean.class
            );

            return response.getBody() != null && response.getBody();
        } catch (Exception e) {
            return false;
        }
    }


    public String getPostOwnerNic(Integer postID, String authHeader) {
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


    //this help get full post from post-service based on post ID(get post controller GET method)
    public Post getPostById(Integer postID, String authHeader) {
        try {
            String url = postServiceUrl + "/api/v1/post/getPostData/" + postID;

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authHeader);

            HttpEntity<?> entity = new HttpEntity<>(headers);

            ResponseEntity<Post> response = restTemplate.exchange(url, HttpMethod.GET, entity, Post.class);

            logger.info("Post response: {}", response.getBody());
            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw new ResponseStatusException(ex.getStatusCode(), "Post service error: " + ex.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch post details: " + e.getMessage());
        }
    }

    public PostViewDto getPostSummary(Integer postID, String authHeader) {
        try {
            String url = postServiceUrl + "/api/v1/post/summary/getPostview/" + postID;
            HttpHeaders headers = new HttpHeaders();// Create HttpHeaders object to set HTTP request headers
            // Add the Authorization header with the bearer token received from the client
           // This ensures the downstream service (user-service) can authenticate and authorize the request
            headers.set("Authorization", authHeader);
           // Create an HttpEntity with the headers to include them in the HTTP request
            HttpEntity<?> entity = new HttpEntity<>(headers);
            // Use RestTemplate.exchange() to send a GET request with headers and receive a response of type PostSummaryDTO
               // exchange() allows setting headers, unlike getForObject()
            ResponseEntity<PostViewDto> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    PostViewDto.class
            );

            return response.getBody();
        } catch (Exception e) {
            System.out.println("Failed to fetch post info: " + e.getMessage());
            return null;
        }
    }

    public PostViewDto getBidforMeSummary(Integer postID, String bidderNIC, String authHeader) {
        try {
            String url = postServiceUrl + "/api/v1/post/summary/getBidforMepostView/" + postID + "/" + bidderNIC;

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authHeader);

            HttpEntity<?> entity = new HttpEntity<>(headers);

            ResponseEntity<PostViewDto> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    PostViewDto.class
            );

            return response.getBody();
        } catch (Exception e) {
            System.out.println("Failed to fetch post info: " + e.getMessage());
            return null;
        }
    }

}
