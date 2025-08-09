package com.hartcircle.post.controller;

import com.hartcircle.post.dto.PostViewDto;
import com.hartcircle.post.service.GetMyPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class GetMyPostsController {   //This show all posts from Post database with related to my NIC

    @Autowired
    private GetMyPostsService getMyPostsService;
    @GetMapping("/getMyPosts")
    public ResponseEntity<List<PostViewDto>> renderMyposts(Authentication auth){
        String userNic=auth.getName();
        System.out.println("NIC "+userNic);
        try{
            List<PostViewDto> myPosts=getMyPostsService.renderMyownPosts(userNic);
            return ResponseEntity.ok(myPosts);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); //return null list.
        }
    }

}
