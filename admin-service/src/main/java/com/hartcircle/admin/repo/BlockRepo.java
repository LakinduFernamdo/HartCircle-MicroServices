package com.hartcircle.admin.repo;

import com.hartcircle.admin.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BlockRepo extends JpaRepository<BlackList,Integer> {

    Optional<BlackList> findByUserNic(String userNic);
}
