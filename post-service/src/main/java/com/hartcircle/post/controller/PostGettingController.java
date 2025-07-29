package com.hartcircle.post.controller;

import com.hartcircle.post.dto.PostViewDto;
import com.hartcircle.post.service.GetItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostGettingController {

    @Autowired
    private GetItem getItem;

    @GetMapping("/item_Type")
    public ResponseEntity<?> sortPosts(@RequestParam("type") String type){
        try {
            List<PostViewDto> filteredPost=getItem.SortPostbyType(type);
            return ResponseEntity.ok(filteredPost);

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());

        }
    }


}
