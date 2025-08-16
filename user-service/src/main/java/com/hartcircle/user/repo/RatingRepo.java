package com.hartcircle.user.repo;


import com.hartcircle.user.entity.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingRepo extends JpaRepository<Ratings, Integer> {
   Optional<Ratings> findByPostIDAndRaterNIC(Integer postID, String raterNIC);
}
