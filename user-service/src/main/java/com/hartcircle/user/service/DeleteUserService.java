package com.hartcircle.user.service;

import com.hartcircle.user.entity.User;
import com.hartcircle.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DeleteUserService {

    @Autowired
    private UserRepository userRepository;

    public void DeleteMyAccount(int userID, String userNIC) {
        // Optional: Confirm userID and userNIC match
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getNic().equals(userNIC)) {
            throw new RuntimeException("You are not authorized to delete this account");
        }

        userRepository.deleteById(userID);
    }
}
