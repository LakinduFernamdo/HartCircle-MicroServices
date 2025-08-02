package com.hartcircle.post.controller;

import com.hartcircle.post.entity.Post;
import com.hartcircle.post.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/post")

public class PostController {

    @Autowired
    private PostRepository postRepository;
    @GetMapping("/post/{postID}/owner")
    public ResponseEntity<String> getPostOwnerNic(@PathVariable Integer postID) {
        Post post = postRepository.findByPostId(postID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));

        return ResponseEntity.ok(post.getUserNIC()); // assuming `userNIC` stores the owner ID
    }
}
