package com.hartcircle.user.controller;

import com.hartcircle.user.service.JwtClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class LogoutController {

    @Autowired
    private JwtClient jwtClient;

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            boolean success = jwtClient.logout(token);
            return success
                    ? ResponseEntity.ok("Logged out successfully")
                    : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Logout failed");
        } else {
            return ResponseEntity.badRequest().body("Invalid Authorization header");
        }
    }
}
