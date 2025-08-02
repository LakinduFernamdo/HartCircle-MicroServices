package com.hartcircle.bid.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "BidInfo")
public class BidInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BidID")
    private Integer bidID;


    @Column(name = "PostID")
    private Integer postID;

    @Column(name = "BidderNIC", nullable = false)
    private String bidderNIC;

    @Column(name = "PostOwnerNIC")
    private String postOwnerNIC;


    @Column(name = "BidedDate")
    private LocalDate BidDate;

    @Column(name = "BidedTime")
    private Time BidTime;

    @Column(name = "Bid_Amount")
    private Double Amount;

    public Integer getBidID() {
        return bidID;
    }

    public void setBidID(Integer bidID) {
        this.bidID = bidID;
    }

    public Integer getPostID() {
        return postID;
    }

    public void setPostID(Integer postID) {
        this.postID = postID;
    }

    public String getBidderNIC() {
        return bidderNIC;
    }

    public void setBidderNIC(String bidderNIC) {
        this.bidderNIC = bidderNIC;
    }

    public String getPostOwnerNIC() {
        return postOwnerNIC;
    }

    public void setPostOwnerNIC(String postOwnerNIC) {
        this.postOwnerNIC = postOwnerNIC;
    }

    public LocalDate getBidDate() {
        return BidDate;
    }

    public void setBidDate(LocalDate bidDate) {
        BidDate = bidDate;
    }

    public Time getBidTime() {
        return BidTime;
    }

    public void setBidTime(Time bidTime) {
        BidTime = bidTime;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }
}
