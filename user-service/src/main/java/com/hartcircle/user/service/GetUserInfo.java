package com.hartcircle.user.service;

import com.hartcircle.user.dto.UserSummaryDTO;
import com.hartcircle.user.entity.User;
import com.hartcircle.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserInfo {

    @Autowired
    private UserRepository userRepository;

    public UserSummaryDTO getUserData(String nic) {
        User user = userRepository.findByNic(nic)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserSummaryDTO dto = new UserSummaryDTO();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setTpNumber(user.getTpNumber());
        dto.setAddress(user.getAddress());
        dto.setUserProfile("http://localhost:8080/api/v1/user/me/image/" + user.getUserId());

        return dto;
    }

    public byte[] getProfileImage(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getImage(); //  profile image is stored in DB
    }
}
