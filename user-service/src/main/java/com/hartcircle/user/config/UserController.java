package com.hartcircle.user.config;

import com.hartcircle.user.dto.UserSummaryDTO;
import com.hartcircle.user.entity.User;
import com.hartcircle.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Handle requests to check if a user exists by NIC.Help another classes.Reply to UserClient.java class in Post-Service.
//help find user via nic

@RestController
@RequestMapping("/api/v1/user")  // ✅ This is the correct prefix
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{nic}")
    public ResponseEntity<Boolean> checkUserExists(@PathVariable("nic") String nic) {
        boolean exists = userRepository.findByNic(nic).isPresent();//return user is on database with this nic or no.
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/summary/{nic}")
    public ResponseEntity<UserSummaryDTO> getUserSummary(@PathVariable("nic") String nic){
        User user = userRepository.findByNic(nic)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserSummaryDTO dto = new UserSummaryDTO();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setTpNumber(user.getTpNumber());
        dto.setAddress(user.getAddress());

        // Assuming you store profile image URL or ID
        dto.setUserProfile("http://localhost:8080/api/v1/user/image/" + user.getUserId());

        return ResponseEntity.ok(dto);
    }


}


