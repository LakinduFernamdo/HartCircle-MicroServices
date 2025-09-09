package com.hartcircle.admin.services;

import com.hartcircle.admin.dto.Logindto;
import com.hartcircle.admin.entity.Admin;
import com.hartcircle.admin.repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private JwtClient jwtClient;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminRepo adminRepo;

    public String validateToken(Logindto logindto){

        Admin admin=adminRepo.findByNic(logindto.getNic())
                .orElseThrow(() -> new RuntimeException("Admin not found !"));

        if(!passwordEncoder.matches(logindto.getPassword(), admin.getPassword())){
            throw new RuntimeException("Invalid password");
        }
      return jwtClient.generateToken(admin.getNic());
    }
}
