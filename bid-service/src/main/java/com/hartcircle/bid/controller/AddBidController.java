package com.hartcircle.bid.controller;

import com.hartcircle.bid.dto.BidDataDTO;
import com.hartcircle.bid.service.BidService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bid")
public class AddBidController {

    @Autowired
    private BidService bidService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @PostMapping("/place/{postID}")
    public ResponseEntity<String> placeBid(@PathVariable("postID") Integer postID,
                                           @RequestBody BidDataDTO dto,
                                           Authentication authentication) {
        try {
            String bidderNic = authentication.getName(); // from JWT
            String authHeader = httpServletRequest.getHeader("Authorization");
            bidService.placeBid(postID, bidderNic, dto.getBidAmount(),authHeader);
            return ResponseEntity.ok("Bid placed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }
}


