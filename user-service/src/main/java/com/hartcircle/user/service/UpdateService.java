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

        // Check if NIC is changed
        if (!existingUser.getNic().equals(updateUserDTO.getNic())) {
            Optional<User> userWithSameNic = userRepository.findByNicAndNotUserId(updateUserDTO.getNic(), userID);
            if (userWithSameNic.isPresent()) {
                throw new RuntimeException("NIC already registered by another user.");
            }
            existingUser.setNic(updateUserDTO.getNic());
        }

        existingUser.setFirstName(updateUserDTO.getFirstName());
        existingUser.setLastName(updateUserDTO.getLastName());
        existingUser.setAddress(updateUserDTO.getAddress());
        existingUser.setEmail(updateUserDTO.getEmail());
        existingUser.setTpNumber(updateUserDTO.getTpNumber());
        existingUser.setPassword(updateUserDTO.getPassword());
        existingUser.setDOB(updateUserDTO.getDOB());

        if (updateUserDTO.getPassword() != null && !updateUserDTO.getPassword().isBlank()) {
            String encodedPassword = passwordEncoder.encode(updateUserDTO.getPassword());
            existingUser.setPassword(encodedPassword);
        }

        if (updateUserDTO.getImage() != null && !updateUserDTO.getImage().isEmpty()) {
            existingUser.setImage(updateUserDTO.getImage().getBytes());
        }

        userRepository.save(existingUser);
    }

}
