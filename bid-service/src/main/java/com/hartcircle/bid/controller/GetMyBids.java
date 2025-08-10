package com.hartcircle.bid.controller;

//This will render bids wich login user bid for any other.(login user's own bid)

import com.hartcircle.bid.dto.MyBiddedInfo;
import com.hartcircle.bid.service.SelectMyOwnBids;
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
public class GetMyBids {

    @Autowired
    private SelectMyOwnBids selectMyOwnBids;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("/getMyBids")
    public ResponseEntity<List<MyBiddedInfo>> getMyBids(Authentication auth){
        String userNIC = auth.getName();
        String authHeader = httpServletRequest.getHeader("Authorization"); // get token from request

        try {
            List<MyBiddedInfo> posts = selectMyOwnBids.renderMybidedPosts(userNIC, authHeader);
            return ResponseEntity.ok(posts);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

