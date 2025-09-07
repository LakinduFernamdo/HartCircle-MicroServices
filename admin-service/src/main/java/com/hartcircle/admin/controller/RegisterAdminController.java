package com.hartcircle.admin.controller;

import com.hartcircle.admin.dto.RegisterDTO;
import com.hartcircle.admin.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class RegisterAdminController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/registerAdmin")
    public ResponseEntity<String> registerAdmin(@ModelAttribute RegisterDTO registerDTO){
        try{
            registerService.registerService(registerDTO);
            return ResponseEntity.ok("Admin registered successfully!");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration  failed "+e.getMessage());
        }
    }

}
