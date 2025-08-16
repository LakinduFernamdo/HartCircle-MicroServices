package com.hartcircle.post.service;

import com.hartcircle.user.dto.UserSummaryDTO;
import com.hartcircle.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;


/*Ask user-service whether a NIC exists in the database
 Purpose: Before saving a post, make sure the user exists
 This is ask and listen user-service to make sure about user deatails
*/

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
    //when post loaded the post owner also get
    public UserSummaryDTO getUserSummary(String nic) {
        try {
            String url = userServiceUrl + "/api/v1/user/summary/" + nic;
            return restTemplate.getForObject(url, UserSummaryDTO.class);
        } catch (Exception e) {
            System.out.println("Failed to fetch user info: " + e.getMessage());
            return null;
        }
    }



}
