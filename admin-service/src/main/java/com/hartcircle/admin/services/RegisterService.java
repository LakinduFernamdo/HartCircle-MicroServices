package com.hartcircle.admin.services;

import com.hartcircle.admin.dto.RegisterDTO;
import com.hartcircle.admin.entity.Admin;
import com.hartcircle.admin.repo.AdminRepo;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RegisterService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminRepo adminRepo;

    public RegisterService(PasswordEncoder passwordEncoder, AdminRepo adminRepo) {
        this.passwordEncoder = passwordEncoder;
        this.adminRepo = adminRepo;
    }

    public void registerService(@NotNull RegisterDTO registerDTO)throws IOException {

        if(adminRepo.findByNic(registerDTO.getNic()).isPresent()){
            throw new RuntimeException("This NIC already used !");
        }

        Admin adminDatabase=new Admin();
        adminDatabase.setFirstName(registerDTO.getFirstName());
        adminDatabase.setLastName(registerDTO.getLastName());
        adminDatabase.setEmail(registerDTO.getEmail());
        adminDatabase.setTpNumber(registerDTO.getTpNumber());
        adminDatabase.setNic(registerDTO.getNic());
        adminDatabase.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        adminDatabase.setImage(registerDTO.getImage().getBytes());

        if (registerDTO.getImage() != null && !registerDTO.getImage().isEmpty()) {
            adminDatabase.setImage(registerDTO.getImage().getBytes());
        }
        adminRepo.save(adminDatabase);
    }
}
