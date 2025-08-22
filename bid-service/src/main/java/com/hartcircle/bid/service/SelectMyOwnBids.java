package com.hartcircle.bid.service;

import com.hartcircle.bid.dto.MyBiddedInfo;
import com.hartcircle.bid.entity.BidInformation;
import com.hartcircle.bid.repo.BidRepo;
import com.hartcircle.bid.dto.PostViewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class SelectMyOwnBids {

    @Autowired
    private BidRepo bidRepo;

    @Autowired
    private PostClient postClient; // calls post-service

    public List<MyBiddedInfo> renderMybidedPosts(String userNIC, String authHeader) {
        // get all bids from DB
        List<BidInformation> bids = bidRepo.findByBidderNIC(userNIC);

        return bids.stream().map(bid -> {
            MyBiddedInfo dto = new MyBiddedInfo();
            dto.setBiddedAmount(bid.getAmount());
            dto.setMybidedDay(bid.getBidDate());
            dto.setMyBidedTime(bid.getBidTime());

            // call post-service -> returns PostViewDto with user info already inside
            PostViewDto postView = postClient.getPostSummary(bid.getPostID(),authHeader);

            System.out.println("Fetched post for bidID " + bid.getBidID() + ": " + postView);
            // set post data directly
            dto.setPost(postView); // change MyBiddedInfo to have PostViewDto post instead of just user

            return dto;
        }).collect(Collectors.toList());
    }
}




