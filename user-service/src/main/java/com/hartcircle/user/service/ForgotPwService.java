package com.hartcircle.user.service;

import com.hartcircle.user.entity.OTPData;
import com.hartcircle.user.entity.User;
import com.hartcircle.user.repo.PasswordResetOTPRepository;
import com.hartcircle.user.repo.UserRepository;
import org.jetbrains.annotations.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class ForgotPwService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetOTPRepository otpRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void sendOTPtoUserEmail(@NotNull String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Email not registered! use Registered email");
        }
        // Generate 6-digit OTP
        String otpCode = String.format("%06d", new Random().nextInt(999999));
        // Save to DB
        OTPData otp = new OTPData();
        otp.setEmail(email);
        otp.setOtp(otpCode);
        otp.setExpiration(LocalDateTime.now().plusMinutes(10));
        otpRepository.save(otp);

        // Send email to user please reser your pw with OTP
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP for Password Reset");
        message.setText("Your OTP is: " + otpCode + "\nIt will expire in 10 minutes.");
        javaMailSender.send(message);


    }

    public void verifyOtpAndResetPassword(String email, String otp, String newPassword) {

        OTPData savedOtp = otpRepository.findByEmailAndOtp(email, otp);
        if (savedOtp == null || savedOtp.getExpiration().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Invalid or expired OTP.");
        }

        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found.");
        }

        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        otpRepository.delete(savedOtp); // Remove used OTP

    }
}
