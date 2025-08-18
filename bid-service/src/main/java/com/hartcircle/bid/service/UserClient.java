package com.hartcircle.bid.service;


import com.hartcircle.post.dto.PostViewDto;
import com.hartcircle.user.dto.UserSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//check the user exist or no
@Service
public class UserClient {

    @Value("${user.service.url}")
    private String userServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    public boolean userExists(String nic) {
        try {
            String url = userServiceUrl + "/api/v1/user/" + nic;
            ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);
            return response.getBody() != null && response.getBody();
        } catch (Exception e) {
            return false;
        }
    }

    public UserSummaryDTO getUserSummary(String nic, String authHeader) {
        try {
            String url = userServiceUrl + "/api/v1/user/summary/" + nic;

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authHeader);

            HttpEntity<?> entity = new HttpEntity<>(headers);

            ResponseEntity<UserSummaryDTO> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    UserSummaryDTO.class
            );

            return response.getBody();
        } catch (Exception e) {
            System.out.println("Failed to fetch user info: " + e.getMessage());
            return null;
        }
    }





}
