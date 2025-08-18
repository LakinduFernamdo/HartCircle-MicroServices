package com.hartcircle.user.dto;

import com.hartcircle.user.entity.Ratings;

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
    public UserSummaryDTO(Integer userID,Date DOB, String firstName, String lastName, String tpNumber, String address, String userProfile,Double myAvgRateValue) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tpNumber = tpNumber;
        this.address = address;
        this.userProfile = userProfile;
        this.dob=DOB;
        this.myAvgRateValue = myAvgRateValue;
    }

    public UserSummaryDTO(){

    }

    public Double getMyAvgRateValue() {
        return myAvgRateValue;
    }

    public void setMyAvgRateValue(Double myAvgRateValue) {
        this.myAvgRateValue = myAvgRateValue;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTpNumber() {
        return tpNumber;
    }

    public void setTpNumber(String tpNumber) {
        this.tpNumber = tpNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
