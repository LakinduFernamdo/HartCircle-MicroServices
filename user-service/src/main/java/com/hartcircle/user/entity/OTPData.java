package com.hartcircle.user.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="OTPInformation")
public class OTPData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Otp_ID") // optional, matches column name
    private Integer otpID;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "OTP", nullable = false)
    private String otp;

    @Column(name = "Exp_Date", nullable = false)
    private LocalDateTime expiration;

    public Integer getOtpID() {
        return otpID;
    }

    public void setOtpID(Integer otpID) {
        this.otpID = otpID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }
}
