package com.hartcircle.user.controller;

import com.hartcircle.user.dto.UpdateUserDTO;
import com.hartcircle.user.dto.UserRegisterDTO;
import com.hartcircle.user.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UpdateUserController {


    @Autowired
    private UpdateService updateService;

    @PatchMapping(value = "/update-user/{userID}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUser(@ModelAttribute UpdateUserDTO updateUserDTO,
                                             @PathVariable Integer userID) {
        try {
            updateService.UpdateUser(updateUserDTO, userID);
            return ResponseEntity.ok("User Updated Successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Update failed: " + e.getMessage());
        }
    }


}
