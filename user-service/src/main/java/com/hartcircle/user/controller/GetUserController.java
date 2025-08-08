package com.hartcircle.user.controller;


import com.hartcircle.user.dto.UserSummaryDTO;
import com.hartcircle.user.service.GetUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class GetUserController {

    @Autowired
    private GetUserInfo getUserInfo;

    @GetMapping("/me")
    public ResponseEntity<?> getUserInformation(Authentication authentication) {          //user authenticated by JWT
        try {
            authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails userDetails)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized or logged out");
            }

            UserDetails userdetails = (UserDetails) authentication.getPrincipal();
            String nic = userdetails.getUsername();
            UserSummaryDTO userDto = getUserInfo.getUserData(nic);
            return ResponseEntity.ok(userDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}
