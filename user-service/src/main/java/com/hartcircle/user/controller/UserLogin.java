package com.hartcircle.user.controller;


import com.hartcircle.user.dto.LoginDTO;

import com.hartcircle.user.service.UserLoginServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/user")

public class UserLogin {

    @Autowired
    private UserLoginServices loginService;

    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody LoginDTO loginDto) {
        try {
            String token = loginService.validateUser(loginDto);
            return ResponseEntity.ok("Login successful! Token: " + token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Login failed: " + e.getMessage());
        }
    }

    @GetMapping("/secure")
    public ResponseEntity<String> secureEndpoint() {
        return ResponseEntity.ok("You are authenticated!");
    }
}