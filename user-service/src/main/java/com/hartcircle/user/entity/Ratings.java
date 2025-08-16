package com.hartcircle.user.entity;

import jakarta.persistence.*;

@Entity
@Table(name="ratingsdata")
public class Ratings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;   // primary key

    @Column(nullable = false)
    private String raterNIC;   // Who gave the rating

    @Column(nullable = false)
    private String ownerNIC;   // Who owns the post

    @Column(nullable = false)
    private Integer postID;    // Which post is rated

    @Column(nullable = false)
    private Double rateValue;  // Rating value

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRaterNIC() {
        return raterNIC;
    }

    public void setRaterNIC(String raterNIC) {
        this.raterNIC = raterNIC;
    }

    public String getOwnerNIC() {
        return ownerNIC;
    }

    public void setOwnerNIC(String ownerNIC) {
        this.ownerNIC = ownerNIC;
    }

    public Integer getPostID() {
        return postID;
    }

    public void setPostID(Integer postID) {
        this.postID = postID;
    }

    public Double getRateValue() {
        return rateValue;
    }

    public void setRateValue(Double rateValue) {
        this.rateValue = rateValue;
    }
}





