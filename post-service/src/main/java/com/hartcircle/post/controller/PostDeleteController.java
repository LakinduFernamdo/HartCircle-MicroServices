package com.hartcircle.post.controller;

import com.hartcircle.post.service.DeletePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/post")
public class PostDeleteController {

    @Autowired
    private DeletePostService deletePostService;
    @DeleteMapping("/deletePost/{postID}")
    public ResponseEntity<String> deletePost(@PathVariable("postID") int postID, Authentication authentication) {
        String userNIC = (String) authentication.getPrincipal();

        try {
            deletePostService.deleteMypostfromDB(postID,userNIC);
            return ResponseEntity.ok("Your Post Deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }


    }
}
