package com.hartcircle.user.controller;


import com.hartcircle.user.dto.RatingDTO;
import com.hartcircle.user.service.RatingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class RatingController {

    @Autowired
    private RatingService ratingService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @PostMapping("/ratethisPost/{postID}")
    public ResponseEntity<String> ratingPosts(Authentication auth, @PathVariable("postID")int postID, @RequestBody RatingDTO ratingDTO){
        String userNIC=auth.getName();
        String authHeader = httpServletRequest.getHeader("Authorization");
        try{
            ratingService.giveRatesForPosts(userNIC,postID,ratingDTO,authHeader);
            return ResponseEntity.ok("Thanks for your Rating!");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Rating Upload failed: " + e.getMessage());
        }

    }
}
