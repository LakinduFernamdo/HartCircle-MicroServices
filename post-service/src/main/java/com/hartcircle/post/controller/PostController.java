package com.hartcircle.post.controller;

import com.hartcircle.post.dto.PostViewDto;
import com.hartcircle.post.entity.Post;
import com.hartcircle.post.repo.PostRepository;
import com.hartcircle.post.service.UserClient;
import com.hartcircle.user.dto.UserSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/post")

public class PostController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserClient userClient;

    @GetMapping("/{postID}/owner")
    public ResponseEntity<String> getPostOwnerNic(@PathVariable("postID") Integer postID) {
        Post post = postRepository.findByPostID(postID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));

        return ResponseEntity.ok(post.getUserNIC()); // assuming `userNIC` stores the owner ID
    }
    @GetMapping("/{postID}")
    public ResponseEntity<Boolean> checkPostExists(@PathVariable("postID") Integer postID) {
        boolean exists = postRepository.findByPostID(postID).isPresent();
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/getPostData/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable("postId") Integer postId) {
        return postRepository.findById(postId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
    }

    @GetMapping("/summary/getPostview/{postId}")
    public ResponseEntity<PostViewDto> getPostViewbyID(@PathVariable("postId") Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        PostViewDto dto = new PostViewDto();
        dto.setStartDate(post.getStartDate());
        dto.setStartTime(post.getStartTime());
        dto.setEndDate(post.getEndDate());
        dto.setEndTime(post.getEndTime());
        dto.setBidLimit(post.getBidLimit());
        dto.setItemType(post.getItemType());
        dto.setDescription(post.getDescription());
        dto.setImage1Url("http://localhost:8081/api/v1/post/image/" + post.getPostID() + "/1");
        dto.setImage2Url("http://localhost:8081/api/v1/post/image/" + post.getPostID() + "/2");

        String userNic = post.getUserNIC();

        // You may need to pass auth header if required by user service
        UserSummaryDTO userSummary = userClient.getUserSummary(userNic);
        dto.setUser(userSummary);
        return ResponseEntity.ok(dto);
    }


}
