package com.hartcircle.user.controller;


import com.hartcircle.user.dto.UserRegisterDTO;
import com.hartcircle.user.service.UserRegisterServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/user")

public class UserRegister {

    @Autowired
    private UserRegisterServise userService;
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@ModelAttribute UserRegisterDTO userDTO) throws IOException {
       try{
           userService.registerUser(userDTO);
           return ResponseEntity.ok("User registered successfully");

       }catch(Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body("Registration failed: " + e.getMessage());

       }
    }

}
