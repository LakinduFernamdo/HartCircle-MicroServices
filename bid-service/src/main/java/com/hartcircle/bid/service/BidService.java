package com.hartcircle.bid.service;


import com.hartcircle.bid.entity.BidInformation;
import com.hartcircle.bid.repo.BidRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

//This is upload data to database

@Service
public class BidService {

    @Autowired
    private BidRepo bidRepo;

    @Autowired
    private UserClient userClient;

    @Autowired
    private PostClient postClient;

    public void placeBid(Integer postID, String bidderNic, Double bidAmount, String authHeader) {
        if (!userClient.userExists(bidderNic)) {
            throw new RuntimeException("Invalid user.");
        }

        if (!postClient.postExist(postID,authHeader)) {
            throw new RuntimeException("Invalid post.");
        }

        String postOwnerNic = postClient.getPostOwnerNic(postID,authHeader);

        if (bidderNic.equals(postOwnerNic)) {
            throw new RuntimeException("You cannot bid on your own post.");
        }
        LocalDate todayDate = LocalDate.now();

        BidInformation bid = new BidInformation();
        bid.setPostID(postID);
        bid.setBidderNIC(bidderNic);
        bid.setPostOwnerNIC(postOwnerNic);
        bid.setAmount(bidAmount);
        bid.setBidDate(Date.valueOf(todayDate));
        bid.setBidTime(new Time(System.currentTimeMillis()));

        bidRepo.save(bid);
    }

}
