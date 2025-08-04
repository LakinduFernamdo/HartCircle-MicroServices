package com.hartcircle.post.dto;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Time;

public class PostUpdateDTO {
    private Time startTime;
    private Time endTime;
    private Date startDate;
    private Date endDate;
    private Integer bidLimit;
    private String itemType;
    private String description;
    private MultipartFile image1Url;
    private MultipartFile image2Url;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImage1Url() {
        return image1Url;
    }

    public void setImage1Url(MultipartFile image1Url) {
        this.image1Url = image1Url;
    }

    public MultipartFile getImage2Url() {
        return image2Url;
    }

    public void setImage2Url(MultipartFile image2Url) {
        this.image2Url = image2Url;
    }
}
