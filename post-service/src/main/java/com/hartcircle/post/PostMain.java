package com.hartcircle.post;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EntityScan(basePackages = "com.hartcircle.post.entity")
@EnableJpaRepositories(basePackages = "com.hartcircle.post.repo")
@ComponentScan(basePackages = "com.hartcircle.post")  // only scan post-service packages
public class PostMain {

    public static void main(String[] args) {
        SpringApplication.run(PostMain.class, args);
    }

    // Required to make JwtClient work
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
