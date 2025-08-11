package com.hartcircle.sms;

import com.hartcircle.sms.config.TwilioConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
public class SmsMain {
    public static void main(String[] args) {
        SpringApplication.run(SmsMain.class, args);
    }
}


