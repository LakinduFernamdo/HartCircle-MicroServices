package com.hartcircle.sms.dto;

import lombok.Data;

@Data
public class SmsDTO {
    private String to;
    private String body;
}