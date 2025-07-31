package com.hartcircle.user.controller;


import com.hartcircle.user.dto.ForgotPWDto;
import com.hartcircle.user.dto.VerifyOtpDto;
import com.hartcircle.user.service.ForgotPwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class ForgotPWController {

    @Autowired
    private ForgotPwService forgotPwService;

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPWDto forgotPWDto) {
        try {
            forgotPwService.sendOTPtoUserEmail(forgotPWDto.getEmailRecover());
            return ResponseEntity.ok("OTP has been sent to your email.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/verify-OTP")
    public ResponseEntity<String> verifyOTP(@RequestBody VerifyOtpDto dto) {
        try {
            forgotPwService.verifyOtpAndResetPassword(
                    dto.getEmail(),
                    dto.getOtp(),
                    dto.getNewPassword()
            );
            return ResponseEntity.ok("Password reset successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}



