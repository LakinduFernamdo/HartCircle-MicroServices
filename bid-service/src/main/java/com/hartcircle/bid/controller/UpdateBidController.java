package com.hartcircle.bid.controller;

import com.hartcircle.bid.dto.BidUpdateDTO;
import com.hartcircle.bid.service.BidUpdateService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bid")
public class UpdateBidController {

    @Autowired
    private BidUpdateService bidUpdateService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @PatchMapping("/changeMybid/{postID}")
    public ResponseEntity<String> updateBid(@RequestBody BidUpdateDTO bidUpdateDTO,
                                            @PathVariable("postID") int postID,
                                            Authentication authentication) {
        try {
            String userNIC = authentication.getName();
            String authHeader = httpServletRequest.getHeader("Authorization");  // get Bearer token here

            bidUpdateService.updateMyOwnBidByPostID(bidUpdateDTO, postID, userNIC, authHeader);
            return ResponseEntity.ok("Bid updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Bid update failed: " + e.getMessage());
        }
    }

}

