package com.hartcircle.user.controller;

import com.hartcircle.user.service.DeleteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class DeleteUserController {

    @Autowired
    private DeleteUserService deleteUserService;

    @DeleteMapping("/deleteMyAccount/{userID}")
    public ResponseEntity<String> deleteAccount(@PathVariable("userID") int userID, Authentication auth) {
        String userNIC = ((UserDetails) auth.getPrincipal()).getUsername();
        try {
            deleteUserService.DeleteMyAccount(userID, userNIC);
            return ResponseEntity.ok("Your Account Deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

}
