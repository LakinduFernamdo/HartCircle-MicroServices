package com.hartcircle.sms.service;

import com.hartcircle.sms.config.TwilioConfig;
import com.hartcircle.sms.dto.SmsDTO;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Autowired
    private TwilioConfig config;

    public void sendSMS(SmsDTO dto) {
        Twilio.init(config.getAccountSid(), config.getAuthToken());

        Message.creator(
                new PhoneNumber(dto.getTo()),
                new PhoneNumber(config.getPhoneNumber()),
                dto.getBody()
        ).create();
    }
}
