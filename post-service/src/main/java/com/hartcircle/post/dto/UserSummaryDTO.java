package com.hartcircle.post.dto;

import java.sql.Date;

public class UserSummaryDTO {
    private Integer userID;
    private String firstName;
    private String lastName;
    private String tpNumber;
    private Date dob;
    private String address;
    private String userProfile;
    private Double myAvgRateValue;


    public UserSummaryDTO(Integer userID, String firstName, String lastName, String tpNumber, Date dob, String address, String userProfile, Double myAvgRateValue) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tpNumber = tpNumber;
        this.dob = dob;
        this.address = address;
        this.userProfile = userProfile;
        this.myAvgRateValue = myAvgRateValue;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTpNumber() {
        return tpNumber;
    }

    public void setTpNumber(String tpNumber) {
        this.tpNumber = tpNumber;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public Double getMyAvgRateValue() {
        return myAvgRateValue;
    }

    public void setMyAvgRateValue(Double myAvgRateValue) {
        this.myAvgRateValue = myAvgRateValue;
    }
}
