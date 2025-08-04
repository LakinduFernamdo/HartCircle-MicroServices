package com.hartcircle.post.controller;


import com.hartcircle.post.dto.PostUpdateDTO;
import com.hartcircle.post.service.PostUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/post")
public class PostUpdateController {
    @Autowired
    private PostUpdateService postUpdateService;


    @PatchMapping(value = "/update-post/{postID}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<String> updatePost(@ModelAttribute PostUpdateDTO postUpdateDTO, @PathVariable Integer postID, Authentication auth) {
        try {
            String userNIC = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            postUpdateService.UpdatePostData(postUpdateDTO, postID,userNIC);
            return ResponseEntity.ok("Post Updated successfully !");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Update failed: " + e.getMessage());
        }
    }

}
