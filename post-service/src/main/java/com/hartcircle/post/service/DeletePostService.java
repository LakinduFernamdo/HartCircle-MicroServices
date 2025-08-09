package com.hartcircle.post.service;

import com.hartcircle.post.entity.Post;
import com.hartcircle.post.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletePostService {

    @Autowired
    private PostRepository postRepository;

    public void deleteMypostfromDB(int postID,String userNIC) {
        Post existPost=postRepository.findByPostID(postID)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        if(!existPost.getUserNIC().equals(userNIC)){
            throw new RuntimeException("You are not authorized to delete others Post.");
        }
        postRepository.deleteById(postID);

    }

}
