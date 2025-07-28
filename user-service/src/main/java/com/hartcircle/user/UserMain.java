package com.hartcircle.user;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.hartcircle.user")
@EnableJpaRepositories(basePackages = "com.hartcircle.user.repo")
@EntityScan(basePackages = "com.hartcircle.user.entity")
@ComponentScan(basePackages = {
        "com.hartcircle.user",
        "com.hartcircle.jwt.Service"
})


public class UserMain {
    public static void main(String[] args) {
        SpringApplication.run(UserMain.class, args);
    }
}
