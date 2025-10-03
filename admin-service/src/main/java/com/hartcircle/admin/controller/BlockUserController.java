package com.hartcircle.admin.controller;

import com.hartcircle.admin.dto.BlockDTO;
import com.hartcircle.admin.services.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class BlockUserController {

    @Autowired
    private BlockService blockService;

    @PostMapping("/addBlockList")
    public ResponseEntity<String> blockUser(Authentication authentication, @RequestBody BlockDTO blockDTO){
        try{
            String adminNIC= (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            blockService.blockUserbyNIC(blockDTO,adminNIC);
            return ResponseEntity.ok("User Blocked !");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Blocking  failed "+e.getMessage());
        }
    }

}
