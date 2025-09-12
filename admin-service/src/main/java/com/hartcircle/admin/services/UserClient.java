package com.hartcircle.admin.services;

import com.hartcircle.admin.dto.UserSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserClient {

    @Value("${user.service.url}")
    private String userServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    //this listen to UserController from user-service /UserController
    public UserSummaryDTO getUserInformation(String nic){
        try{
            String url=userServiceUrl+ "/api/v1/user/summary/" +nic;
            return restTemplate.getForObject(url, UserSummaryDTO.class);
        }catch(Exception e){
            System.out.println("Failed to fetch user info: " + e.getMessage());
            return null;
        }
    }

   //this listen to UserController which on user-service
    public UserSummaryDTO getUserInformationwithTpNumber(String tpNumber){
        try{
            String url=userServiceUrl+ "/api/v1/user/userdata/" +tpNumber;
            return restTemplate.getForObject(url, UserSummaryDTO.class);
        }catch(Exception e){
            System.out.println("Failed to fetch user info: " + e.getMessage());
            return null;
        }
    }

}
