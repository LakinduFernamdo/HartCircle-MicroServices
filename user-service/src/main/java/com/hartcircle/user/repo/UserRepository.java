package com.hartcircle.user.repo;


import com.hartcircle.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByNic(String nic);
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.nic = :nic AND u.id <> :userID")
    Optional<User> findByNicAndNotUserId(String nic, Integer userID);

}

