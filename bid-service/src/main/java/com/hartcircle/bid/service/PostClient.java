package com.hartcircle.bid.service;


//check it post exist on Database

import com.hartcircle.post.entity.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import static com.mysql.cj.conf.PropertyKey.logger;

@Service
public class PostClient {

    @Value("${post.service.url}")
    private String postServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(PostClient.class);


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
    //this help get full post from post-service based on post ID(get post controller GET method)
    public Post getPostById(Integer postID) {
        try {
            String url = postServiceUrl + "/api/v1/post/getPostData/" + postID;
            ResponseEntity<Post> response = restTemplate.getForEntity(url, Post.class);
            logger.info("Post response: {}", response.getBody());
            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw new ResponseStatusException(ex.getStatusCode(), "Post service error: " + ex.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch post details: " + e.getMessage());
        }
    }


}
