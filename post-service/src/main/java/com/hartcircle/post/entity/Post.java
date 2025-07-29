package com.hartcircle.post.entity;

import jakarta.persistence.*;
import com.hartcircle.*;


import java.sql.Date;
import java.sql.Time;

import com.hartcircle.user.entity.User;


@Entity
@Table(name = "postdata")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PostID")
    private Integer postID;

    @Column(name = "UserNIC", nullable = false)
    private String userNIC;  // instead of User or userID


    @Column(name = "StartTime")
    private Time startTime;

    @Column(name = "EndTime")
    private Time endTime;

    @Column(name = "StartDate")
    private Date startDate;

    @Column(name = "EndDate")
    private Date endDate;

    @Column(name = "BidLimit")
    private Integer bidLimit;

    @Column(name = "ItemType")
    private String itemType;

    @Column(name = "Description")
    private String description;

    @Column(name = "Image1", columnDefinition = "LONGBLOB")
    private byte[] image1;

    @Column(name = "Image2", columnDefinition = "LONGBLOB")
    private byte[] image2;



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage1() {
        return image1;
    }

    public void setImage1(byte[] image1) {
        this.image1 = image1;
    }

    public byte[] getImage2() {
        return image2;
    }

    public void setImage2(byte[] image2) {
        this.image2 = image2;
    }

    public Integer getPostID() {
        return postID;
    }

    public void setPostID(Integer postID) {
        this.postID = postID;
    }

    public String getUserNIC() {
        return userNIC;
    }

    public void setUserNIC(String userNIC) {
        this.userNIC = userNIC;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getBidLimit() {
        return bidLimit;
    }

    public void setBidLimit(Integer bidLimit) {
        this.bidLimit = bidLimit;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
