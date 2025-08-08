package com.hartcircle.post.controller;


import com.hartcircle.post.dto.PostViewDto;
import com.hartcircle.post.service.PublicWallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PublicWallController {

    @Autowired
    private PublicWallService publicWallService;

    @GetMapping("/publicWall")
    public ResponseEntity<List<PostViewDto>> getAllposts() {
        try {
            List<PostViewDto> posts = publicWallService.getEachPostsfromDB();
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // or return empty list
        }
    }
}

po