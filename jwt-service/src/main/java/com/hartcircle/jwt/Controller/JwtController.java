package com.hartcircle.jwt.Controller;



import com.hartcircle.jwt.Dto.JwtRequest;
import com.hartcircle.jwt.Dto.JwtResponse;
import com.hartcircle.jwt.Service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/generate")
    public ResponseEntity<JwtResponse> generateToken(@RequestBody  JwtRequest request) {
        String token = jwtService.generateToken(request.getUserNIC());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestBody  JwtRequest request) {
        boolean isValid = jwtService.validateToken(request.getToken(), request.getUserNIC());
        return ResponseEntity.ok(isValid);
    }
}

