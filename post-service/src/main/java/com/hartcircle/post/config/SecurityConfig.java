package com.hartcircle.post.config;

import com.hartcircle.post.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/v1/user/CreatePost",
                                "/api/v1/post/image/**"
                        ).authenticated()
                        .requestMatchers("/api/v1/post/publicWall").authenticated()
                        .requestMatchers("/api/v1/post/**").authenticated()
                        .requestMatchers("/api/v1/post/summary/getPostview").authenticated()
                        .requestMatchers("/api/v1/post/getPostData/**").authenticated()
                        .requestMatchers("/api/v1/user/summary/**").authenticated()
                        .requestMatchers("/api/v1/post/ratethisPost/**").authenticated()
                        .anyRequest().permitAll()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}