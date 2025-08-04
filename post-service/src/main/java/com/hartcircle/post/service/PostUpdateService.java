package com.hartcircle.post.service;


import com.hartcircle.post.dto.PostUpdateDTO;
import com.hartcircle.post.entity.Post;
import com.hartcircle.post.repo.PostRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PostUpdateService {
    @Autowired
    private final PostRepository postRepository;

    public PostUpdateService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void UpdatePostData(@NotNull PostUpdateDTO postUpdateDTO, Integer postID, String userNIC) throws IOException {
        //check is post exist
        Post existPost = postRepository.findByPostID(postID).
                orElseThrow(() -> new RuntimeException("Post not found !"));
        existPost.setStartTime(postUpdateDTO.getStartTime());
        existPost.setEndDate(postUpdateDTO.getEndDate());
        existPost.setStartDate(postUpdateDTO.getStartDate());
        existPost.setEndTime(postUpdateDTO.getEndTime());
        existPost.setDescription(postUpdateDTO.getDescription());
        existPost.setBidLimit(postUpdateDTO.getBidLimit());
        existPost.setItemType(postUpdateDTO.getItemType());


        existPost.setUserNIC(userNIC);

        if (postUpdateDTO.getImage1Url() != null && !postUpdateDTO.getImage1Url().isEmpty()) {
            existPost.setImage1(postUpdateDTO.getImage1Url().getBytes());
        }

        if (postUpdateDTO.getImage2Url() != null && !postUpdateDTO.getImage2Url().isEmpty()) {
            existPost.setImage2(postUpdateDTO.getImage2Url().getBytes());
        }


        postRepository.save(existPost);


    }


}
