package com.hartcircle.bid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BidMain {
    public static void main(String[] args) {
        SpringApplication.run(BidMain.class, args);
    }
    // Required to make JwtClient work
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
