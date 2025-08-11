package com.hartcircle.bid.service;

import com.hartcircle.bid.dto.MyBiddedInfo;
import com.hartcircle.bid.entity.BidInformation;
import com.hartcircle.bid.repo.BidRepo;
import com.hartcircle.post.dto.PostViewDto;
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

    public List<MyBiddedInfo> RederBidsforMe(String userNIC, String authHeader) {
        //render all posts which postOwnerNIC=userNIC
        List<BidInformation> bids=bidRepo.findByPostOwnerNIC(userNIC);
        return bids.stream().map(bid -> {
            MyBiddedInfo dto=new MyBiddedInfo();
            dto.setBiddedAmount(bid.getAmount());
            dto.setMyBidedTime(bid.getBidTime());
            dto.setMybidedDay(bid.getBidDate());
           // call post-service -> returns PostViewDto with user info already inside the PostViewDTO
            PostViewDto postView=postClient.getPostSummary(bid.getPostID(),userNIC);
            System.out.println("Fetched post for bidID " + bid.getBidID() + ": " + postView);
            // set post data directly
            dto.setPost(postView);
            return dto;
        }).collect(Collectors.toList());
    }
}
