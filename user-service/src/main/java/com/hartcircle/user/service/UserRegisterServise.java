package com.hartcircle.user.service;


import com.hartcircle.user.dto.UserRegisterDTO;
import com.hartcircle.user.entity.User;
import com.hartcircle.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserRegisterServise {

    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRegisterServise(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(@org.jetbrains.annotations.NotNull UserRegisterDTO userDTO) throws IOException {

        //check nic already exist
        Optional<User> existingUser = userRepository.findByNic(userDTO.getNic());
        if (existingUser.isPresent()) {
            throw new RuntimeException("NIC already registered. Please use a different NIC.");
        }
        User user=new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAddress(userDTO.getAddress());
        user.setTpNumber(userDTO.getTpNumber());
        user.setNic(userDTO.getNic());
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encodedPassword);
        user.setDOB(userDTO.getDOB());
        user.setEmail(userDTO.getEmail());
        user.setImage(userDTO.getImage().getBytes());

        if (userDTO.getImage() != null && !userDTO.getImage().isEmpty()) {
            user.setImage(userDTO.getImage().getBytes());
        }

        userRepository.save(user);
    }
}
