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
public class GetBidsFormeService {

    @Autowired
    private BidRepo bidRepo;
    @Autowired
    private PostClient postClient;
    @Autowired
    private UserClient userClient;

    public List<MyBiddedInfo> RederBidsforMe(String userNIC, String authHeader) {
        // All bids placed on my posts
        List<BidInformation> bids = bidRepo.findByPostOwnerNIC(userNIC);

        return bids.stream().map(bid -> {
            MyBiddedInfo dto = new MyBiddedInfo();
            dto.setBiddedAmount(bid.getAmount());
            dto.setMybidedDay(bid.getBidDate());
            dto.setMyBidedTime(bid.getBidTime());
            PostViewDto postView = postClient.getBidforMeSummary(bid.getPostID(), bid.getBidderNIC(), authHeader);
            dto.setPost(postView);

            return dto;
        }).collect(Collectors.toList());
    }

}
