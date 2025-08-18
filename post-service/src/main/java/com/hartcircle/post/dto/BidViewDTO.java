package com.hartcircle.post.dto;

import com.hartcircle.user.dto.UserSummaryDTO;

import java.sql.Date;
import java.sql.Time;

public class BidViewDTO {

    private Double biddedAmount;
    private Date mybidedDay;
    private Time myBidedTime;
    private PostViewDto post;
    private UserSummaryDTO bidder;

    public Double getBiddedAmount() {
        return biddedAmount;
    }

    public void setBiddedAmount(Double biddedAmount) {
        this.biddedAmount = biddedAmount;
    }

    public Date getMybidedDay() {
        return mybidedDay;
    }

    public void setMybidedDay(Date mybidedDay) {
        this.mybidedDay = mybidedDay;
    }

    public Time getMyBidedTime() {
        return myBidedTime;
    }

    public void setMyBidedTime(Time myBidedTime) {
        this.myBidedTime = myBidedTime;
    }

    public PostViewDto getPost() {
        return post;
    }

    public void setPost(PostViewDto post) {
        this.post = post;
    }

    public UserSummaryDTO getBidder() {
        return bidder;
    }

    public void setBidder(UserSummaryDTO bidder) {
        this.bidder = bidder;
    }
}
