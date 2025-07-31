package com.hartcircle.user.controller;


import com.hartcircle.user.service.GetUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/me")
public class UserImageController {

    @Autowired
    private GetUserInfo getUserInfo;

    @GetMapping("/image/{userID}")
    public ResponseEntity<?> getUserProfilePic(@PathVariable("userID") Integer userID) {
        try {
            byte[] imageBytes = getUserInfo.getProfileImage(userID);
            if (imageBytes == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // or IMAGE_PNG
                    .body(imageBytes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}

