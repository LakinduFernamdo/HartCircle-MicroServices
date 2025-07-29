package com.hartcircle.post.controller;


import com.hartcircle.post.dto.PostViewDto;
import com.hartcircle.post.service.GetItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostImageController {

    @Autowired
    private GetItem getItem;

    @GetMapping("/image/{postId}/{slot}")
    public ResponseEntity<?> getImage(
            @PathVariable("postId") Integer postID,
            @PathVariable("slot") Integer slot) {

        try {
            byte[] imageBytes = getItem.getImageBySlot(postID, slot);
            if (imageBytes == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG) // or IMAGE_PNG depending on your data
                    .body(imageBytes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}

