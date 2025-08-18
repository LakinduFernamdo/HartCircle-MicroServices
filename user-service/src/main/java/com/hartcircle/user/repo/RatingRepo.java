package com.hartcircle.user.repo;


import com.hartcircle.user.entity.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RatingRepo extends JpaRepository<Ratings, Integer> {

   Optional<Ratings> findByPostIDAndRaterNIC(Integer postID, String raterNIC);

   @Query("SELECT AVG(r.rateValue) FROM Ratings r WHERE r.ownerNIC = :ownerNIC")
   Double findAverageRatingForUser(@Param("ownerNIC") String ownerNIC);

   //rateValue,ownerNIC are getting from Ratings Entity.
}
