package com.hartcircle.admin.controller;

import com.hartcircle.admin.dto.PostViewDto;
import com.hartcircle.admin.services.GetPostDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class GetPostDeatailsController {

    @Autowired
    private GetPostDataService getPostDataService;

    @GetMapping("/getPosts")
    public ResponseEntity<List<PostViewDto>> getPostsbyNIC(@RequestParam("nic") String nic) {
        try {
            List<PostViewDto> postList = getPostDataService.getPostinforByNIC(nic);
            return ResponseEntity.ok(postList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}



