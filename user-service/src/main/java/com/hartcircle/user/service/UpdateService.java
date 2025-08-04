package com.hartcircle.user.service;

import com.hartcircle.user.dto.UpdateUserDTO;
import com.hartcircle.user.dto.UserRegisterDTO;
import com.hartcircle.user.entity.User;
import com.hartcircle.user.repo.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class UpdateService {               //use register dto for update also

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final  PasswordEncoder passwordEncoder;


    public UpdateService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void UpdateUser(@NotNull UpdateUserDTO updateUserDTO, Integer userID) throws IOException {

        User existingUser = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if NIC is changed if change again check is it already availabale
        if (!existingUser.getNic().equals(updateUserDTO.getNic())) {
            Optional<User> userWithSameNic = userRepository.findByNicAndNotUserId(updateUserDTO.getNic(), userID);
            if (userWithSameNic.isPresent()) {
                throw new RuntimeException("NIC already registered by another user.");
            }
            existingUser.setNic(updateUserDTO.getNic());
        }
        //user not change NIC
        User user=new User();
        user.setFirstName(updateUserDTO.getFirstName());
        user.setLastName(updateUserDTO.getLastName());
        user.setAddress(updateUserDTO.getAddress());
        user.setEmail(updateUserDTO.getEmail());
        String encodedPassword = passwordEncoder.encode(updateUserDTO.getPassword());
        user.setPassword(encodedPassword);
        user.setNic(updateUserDTO.getNic());
        user.setDOB(updateUserDTO.getDOB());
        user.setTpNumber(updateUserDTO.getTpNumber());
        user.setImage(updateUserDTO.getImage().getBytes());
        userRepository.save(user);
    }
}
