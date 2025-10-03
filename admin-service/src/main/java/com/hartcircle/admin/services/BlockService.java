package com.hartcircle.admin.services;

import com.hartcircle.admin.dto.BlockDTO;
import com.hartcircle.admin.entity.Admin;
import com.hartcircle.admin.entity.BlackList;
import com.hartcircle.admin.repo.AdminRepo;
import com.hartcircle.admin.repo.BlockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BlockService {

    @Autowired
    private BlockRepo blockRepo;

    @Autowired
    private AdminRepo adminRepo;

    public void blockUserbyNIC(BlockDTO blockDTO, String adminNIC) {

        // check if admin exists
        Admin findAdmin = adminRepo.findByNic(adminNIC)
                .orElseThrow(() -> new RuntimeException("Admin not Recognized !"));

        // check if user already blocked
        Optional<BlackList> existingBlock = blockRepo.findByUserNic(blockDTO.getUserNic());
        if (existingBlock.isPresent()) {
            throw new RuntimeException("User already Blocked !");
        }

        // create new BlackList entry
        BlackList blackList = new BlackList();
        blackList.setAdminNic(adminNIC);
        blackList.setUserNic(blockDTO.getUserNic());
        blackList.setDate(blockDTO.getDate());
        blackList.setTime(blockDTO.getTime());

        // save
        blockRepo.save(blackList);
    }
}

