package com.hartcircle.user.service;



import com.hartcircle.user.dto.LoginDTO;
import com.hartcircle.user.entity.User;
import com.hartcircle.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserLoginServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtClient jwtClient;

    public String validateUser(LoginDTO loginDto) {
        User user = userRepository.findByNic(loginDto.getNic())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtClient.generateToken(user.getNic());
    }
}


