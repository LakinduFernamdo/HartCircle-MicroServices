package com.hartcircle.admin.services;

import com.hartcircle.admin.dto.UserSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class GetUserdeatailsService {

    @Autowired
    private UserClient userClient;

   //This will return user information by sorting NIC
    public UserSummaryDTO sortNIC(String nic) {

        return userClient.getUserInformation(nic);

    }
    //This will return user information by sorting TpNumber
    public UserSummaryDTO sortMobileNumber(String tpNumber){
        return userClient.getUserInformationwithTpNumber(tpNumber);
    }
}
