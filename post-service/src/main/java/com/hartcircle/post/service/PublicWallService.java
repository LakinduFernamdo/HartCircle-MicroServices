package com.hartcircle.post.service;

import com.hartcircle.post.dto.PostViewDto;
import com.hartcircle.post.entity.Post;
import com.hartcircle.post.repo.PostRepository;
import com.hartcircle.post.dto.UserSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicWallService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserClient userClient;

    public List<PostViewDto> getEachPostsfromDB() {
        List<Post> posts = postRepository.findAll();

        return posts.stream().map(post -> {
            PostViewDto dto = new PostViewDto();
            dto.setPostID(post.getPostID());
            dto.setStartDate(post.getStartDate());
            dto.setStartTime(post.getStartTime());
            dto.setEndTime(post.getEndTime());
            dto.setEndDate(post.getEndDate());
            dto.setBidLimit(post.getBidLimit());
            dto.setItemType(post.getItemType());
            dto.setDescription(post.getDescription());
            dto.setImage1Url("http://localhost:8081/api/v1/post/image/" + post.getPostID() + "/1");
            dto.setImage2Url("http://localhost:8081/api/v1/post/image/" + post.getPostID() + "/2");

            // Get user summary from user service
            UserSummaryDTO userDto = userClient.getUserSummary(post.getUserNIC());
            dto.setUser(userDto);

            return dto;
        }).collect(Collectors.toList());
    }
}

