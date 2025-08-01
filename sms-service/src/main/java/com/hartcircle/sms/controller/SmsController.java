package com.hartcircle.sms.controller;

import com.hartcircle.sms.dto.SmsDTO;
import com.hartcircle.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @PostMapping("/send")
    public ResponseEntity<String> sendSms(@RequestBody SmsDTO smsDTO) {
        try {
            smsService.sendSMS(smsDTO);
            return ResponseEntity.ok("✅ SMS sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("❌ Failed to send SMS: " + e.getMessage());
        }
    }
}
