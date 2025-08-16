package com.hartcircle.user.service;


import com.hartcircle.user.dto.RatingDTO;
import com.hartcircle.user.entity.Ratings;
import com.hartcircle.user.entity.User;
import com.hartcircle.user.repo.RatingRepo;
import com.hartcircle.user.repo.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RatingRepo ratingRepo;

    @Autowired
    private PostClient postClient;

    public void giveRatesForPosts(String raterNIC, int postID, @NotNull RatingDTO ratingDTO, String authHeader) {
        // Check Rater exists
        userRepository.findByNic(raterNIC)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        // Get post owner NIC from post-service
        String ownerNIC = postClient.getUserNIC(postID, authHeader);

        // Prevent duplicate ratings (same user on same post)
        ratingRepo.findByPostIDAndRaterNIC(postID, raterNIC).ifPresent(r -> {
            throw new RuntimeException("You already rated this post!");
        });

        // Save new rating(I can Rate my own posts also)
        Ratings ratings = new Ratings();
        ratings.setRaterNIC(raterNIC);
        ratings.setOwnerNIC(ownerNIC);
        ratings.setPostID(postID);
        ratings.setRateValue(ratingDTO.getRatingValue());

        ratingRepo.save(ratings);
    }
}




