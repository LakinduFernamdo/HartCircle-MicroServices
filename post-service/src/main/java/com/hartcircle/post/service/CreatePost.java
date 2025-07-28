package com.hartcircle.post.service;



import com.hartcircle.post.dto.PostData;
import com.hartcircle.post.entity.Post;
import com.hartcircle.post.repo.PostRepository;
import com.hartcircle.user.entity.User;
import com.hartcircle.user.repo.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class CreatePost {

    @Autowired
    private PostRepository postRepo;
    @Autowired
    private UserClient userClient;


    public void createPost(@NotNull PostData postdataDTO, String userNIC) throws IOException {

        //  Find the logged-in user using NIC(Extract jwt)


        if (!userClient.userExists(userNIC)) {
            throw new RuntimeException("User not found");
        }


        Post post = new Post();

        post.setUserNIC(userNIC);/// ✅ Save NIC directly

        post.setStartTime(postdataDTO.getStartTime());
        post.setEndTime(postdataDTO.getEndTime());
        post.setStartDate(postdataDTO.getStartDate());
        post.setEndDate(postdataDTO.getEndDate());
        post.setBidLimit(postdataDTO.getBidLimit());
        post.setItemType(postdataDTO.getItemType());
        post.setDescription(postdataDTO.getDescription());

        if (postdataDTO.getImage1() != null && !postdataDTO.getImage1().isEmpty()) {
            post.setImage1(postdataDTO.getImage1().getBytes());
        }
        if (postdataDTO.getImage2() != null && !postdataDTO.getImage2().isEmpty()) {
            post.setImage2(postdataDTO.getImage2().getBytes());
        }

        postRepo.save(post);
    }



}
