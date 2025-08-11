package com.hartcircle.sms.controller;


import com.hartcircle.sms.dto.SmsDTO;
import com.hartcircle.sms.service.SmsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping("/api/v1/sms")
public class SmsController {

    private static final Logger log = LoggerFactory.getLogger(SmsController.class);

    @Autowired
    private SmsService smsService;

    @PostMapping("/send")
    public ResponseEntity<String> sendSms(@RequestBody SmsDTO smsDTO) {
        try {
            String sid = smsService.sendSMS(smsDTO);
            log.info("✅ SMS sent to {} with SID: {}", smsDTO.getTo(), sid);
            return ResponseEntity.ok("✅ SMS sent successfully to " + smsDTO.getTo());
        } catch (Exception e) {
            log.error("❌ Error while sending SMS to {}: {}", smsDTO.getTo(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Failed to send SMS: " + e.getMessage());
        }
    }
}
