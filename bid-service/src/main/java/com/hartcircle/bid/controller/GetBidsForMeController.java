package com.hartcircle.bid.controller;


import com.hartcircle.bid.dto.MyBiddedInfo;
import com.hartcircle.bid.service.GetBidsFormeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/bid")
public class GetBidsForMeController { //This class helps  to render who are the people bids for my posts

    @Autowired
    private GetBidsFormeService getBidsFormeService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("/getBidsForMe")
    public ResponseEntity<List<MyBiddedInfo>>  bidsforme(Authentication authentication){
        String userNIC=authentication.getName();
        String authHeader=httpServletRequest.getHeader("Authorization");
        try{
         List<MyBiddedInfo>posts=   getBidsFormeService.RederBidsforMe(userNIC,authHeader);
         return ResponseEntity.ok(posts);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
