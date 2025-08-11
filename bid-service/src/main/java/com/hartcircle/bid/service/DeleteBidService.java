package com.hartcircle.bid.service;


import com.hartcircle.bid.entity.BidInformation;
import com.hartcircle.bid.repo.BidRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Service
@Service
public class DeleteBidService {

    @Autowired
    private BidRepo bidRepo;

    public void deleteMyBid(int bidID, String userNIC) {
        BidInformation bid = bidRepo.findById(bidID)
                .orElseThrow(() -> new RuntimeException("Bid not found"));

        if (!bid.getPostOwnerNIC().equals(userNIC)) {
            throw new RuntimeException("You can delete your own bids only!");
        }

        bidRepo.deleteById(bidID);
    }
}

