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

    public String sendSMS(SmsDTO dto) {
        Twilio.init(config.getAccountSid(), config.getAuthToken());

        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(dto.getTo()),
                new PhoneNumber(config.getPhoneNumber()),
                dto.getBody()
        ).create();

        return message.getSid();
    }
}

