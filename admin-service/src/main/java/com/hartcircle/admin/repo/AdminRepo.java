package com.hartcircle.admin.repo;

import com.hartcircle.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin,Integer> {
    Optional<Admin> findByNic(String nic);
}


