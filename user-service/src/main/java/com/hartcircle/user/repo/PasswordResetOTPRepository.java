package com.hartcircle.user.repo;

import com.hartcircle.user.entity.OTPData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



    @Repository
    public interface PasswordResetOTPRepository  extends JpaRepository<OTPData,Integer> {
        OTPData findByEmailAndOtp(String email, String otp);
    }


