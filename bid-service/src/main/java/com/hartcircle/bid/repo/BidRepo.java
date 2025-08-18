package com.hartcircle.bid.repo;

import com.hartcircle.bid.entity.BidInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepo extends JpaRepository<BidInformation, Integer> {

    List<BidInformation> findByPostIDAndBidderNIC(int postID, String bidderNIC);
    List<BidInformation>findByBidderNIC(String bidderNIC);
    List<BidInformation>findByPostOwnerNIC(String postOwnerNIC);




}