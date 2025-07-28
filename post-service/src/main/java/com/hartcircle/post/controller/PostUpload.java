package com.hartcircle.post.controller;



import com.hartcircle.post.dto.PostData;
import com.hartcircle.post.service.CreatePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/post")
public class PostUpload {

    @Autowired
    private CreatePost postcreate;

    @PostMapping("/CreatePost")
    public ResponseEntity<String> createPost(@ModelAttribute PostData postDataDTO, Authentication authentication) {
        try {
            String userNIC = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            postcreate.createPost(postDataDTO, userNIC);
            return ResponseEntity.ok("Post created successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload failed: " + e.getMessage());
        }
    }






}
