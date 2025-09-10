package com.hartcircle.admin.controller;

import com.hartcircle.admin.dto.UserSummaryDTO;
import com.hartcircle.admin.services.GetUserdeatailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



//search users via NIC or TpNumber
@RestController
@RequestMapping("/api/v1/admin")
public class GetUserInfoController {

    @Autowired
    private GetUserdeatailsService getUserdeatailsService;

    @GetMapping("/user_info")
    public ResponseEntity<?> getUserinfo(@RequestParam("nic") String nic){

        try{
            UserSummaryDTO filteredUser=getUserdeatailsService.sortNIC(nic);
            return ResponseEntity.ok(filteredUser);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

}
