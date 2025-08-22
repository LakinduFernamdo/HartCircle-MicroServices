package com.hartcircle.bid.service;


import com.hartcircle.bid.dto.BidUpdateDTO;
import com.hartcircle.bid.entity.BidInformation;
import com.hartcircle.bid.repo.BidRepo;
import com.hartcircle.bid.dto.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class BidUpdateService {

    @Autowired
    private BidRepo bidRepo;

    @Autowired
    private PostClient postClient;

    public void updateMyOwnBidByPostID(BidUpdateDTO bidUpdateDTO, int postID, String userNIC,String authHeader) {
        // Validate bid amount
        if (bidUpdateDTO.getBidAmount() == null || bidUpdateDTO.getBidAmount() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bid amount must be positive.");
        }

        // Find user's bid for this post
        List<BidInformation> userBids = bidRepo.findByPostIDAndBidderNIC(postID, userNIC);
        if (userBids.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Your bid on this post was not found.");
        }

        // Use the first bid (assuming one bid per post per user)
        BidInformation existBid = userBids.get(0);

        // Get post details
        Post postDetails = postClient.getPostById(postID,authHeader);

        // Get current date and time
        LocalDate todayDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();

        // Extract end date and time from post (assuming java.sql.Date and java.sql.Time)
        LocalDate postEndDate = postDetails.getEndDate().toLocalDate();
        LocalTime postEndTime = postDetails.getEndTime().toLocalTime();

        // Check if bidding deadline has passed
        if (postEndDate.isBefore(todayDate) ||
                (postEndDate.equals(todayDate) && postEndTime.isBefore(nowTime))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Deadline is over. Cannot update bid.");
        }

        // Get the post owner's NIC
        String postOwnerNic = postClient.getPostOwnerNic(postID,authHeader);

        // Update the bid
        existBid.setAmount(bidUpdateDTO.getBidAmount());
        existBid.setBidDate(Date.valueOf(todayDate)); // java.sql.Date
        existBid.setBidTime(Time.valueOf(nowTime));   // java.sql.Time
        existBid.setBidderNIC(userNIC);
        existBid.setPostOwnerNIC(postOwnerNic);

        // Save the updated bid
        bidRepo.save(existBid);
    }

}


