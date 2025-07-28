package com.hartcircle.user.config;

import com.hartcircle.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/v1/user/{nic}")
    public ResponseEntity<Boolean> checkUserExists(@PathVariable String nic) {
        boolean exists = userRepository.findByNic(nic).isPresent();
        return ResponseEntity.ok(exists);
    }
}

