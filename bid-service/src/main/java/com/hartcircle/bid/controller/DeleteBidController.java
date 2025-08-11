package com.hartcircle.bid.controller;

import com.hartcircle.bid.service.DeleteBidService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bid")
public class DeleteBidController {

    @Autowired
    private DeleteBidService deleteBidService;

    @DeleteMapping("/delete/{bidId}")
    public ResponseEntity<String> deleteBid(Authentication authentication, @PathVariable("bidId") int bidID) {
        String userNIC = authentication.getName();

        try {
            deleteBidService.deleteMyBid(bidID, userNIC);
            return ResponseEntity.ok("Your bid was deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }
}
