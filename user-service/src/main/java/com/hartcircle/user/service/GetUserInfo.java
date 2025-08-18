package com.hartcircle.user.service;

import com.hartcircle.user.dto.UserSummaryDTO;
import com.hartcircle.user.entity.User;
import com.hartcircle.user.repo.RatingRepo;
import com.hartcircle.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserInfo {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RatingRepo ratingRepo;

    public UserSummaryDTO getUserData(String nic) {
        User user = userRepository.findByNic(nic)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Double avgRating=ratingRepo.findAverageRatingForUser(nic);
        if(avgRating==null) avgRating=0.0;

        UserSummaryDTO dto = new UserSummaryDTO();
        dto.setUserID(user.getUserId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setTpNumber(user.getTpNumber());
        dto.setDob(user.getDOB());
        dto.setAddress(user.getAddress());
        dto.setMyAvgRateValue(avgRating);
        dto.setUserProfile("http://localhost:8080/api/v1/user/me/image/" + user.getUserId());

        return dto;
    }

    public byte[] getProfileImage(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getImage(); //  profile image is stored in DB
    }
}
