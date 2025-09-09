package com.hartcircle.admin.controller;

import com.hartcircle.admin.dto.Logindto;
import com.hartcircle.admin.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/adminLogin")
    public ResponseEntity<String> login(@RequestBody Logindto logindto){
        try{
            String token=loginService.validateToken(logindto);
            return ResponseEntity.ok("Login successful! Token:"+token);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Login failed: " + e.getMessage());
        }
    }
    //additional case to confirm
    @GetMapping("/secure")
    public ResponseEntity<String> secureEndpoint() {
        return ResponseEntity.ok("You are authenticated!");
    }

}
