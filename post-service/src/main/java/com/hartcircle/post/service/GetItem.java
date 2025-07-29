package com.hartcircle.post.service;

import com.hartcircle.post.dto.PostViewDto;
import com.hartcircle.post.entity.Post;
import com.hartcircle.post.repo.PostRepository;
import com.hartcircle.user.dto.UserSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetItem {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserClient userClient;

    public List<PostViewDto> SortPostbyType(String item_type) {
        List<Post> posts = postRepository.findByItemTypeIgnoreCase(item_type);

        return posts.stream().map(post -> {
            PostViewDto dto = new PostViewDto();

            dto.setPostId(post.getPostID());
            dto.setStartTime(post.getStartTime());
            dto.setEndTime(post.getEndTime());
            dto.setStartDate(post.getStartDate());
            dto.setEndDate(post.getEndDate());
            dto.setBidLimit(post.getBidLimit());
            dto.setItemType(post.getItemType());
            dto.setDescription(post.getDescription());

            dto.setImage1Url("http://localhost:8081/api/v1/post/image/" + post.getPostID() + "/1");
            dto.setImage2Url("http://localhost:8081/api/v1/post/image/" + post.getPostID() + "/2");

            // ✅ Fetch user summary from user-service
            UserSummaryDTO user = userClient.getUserSummary(post.getUserNIC());
            dto.setUser(user);

            return dto;
        }).collect(Collectors.toList());
    }



    public byte[] getImageBySlot(Integer postId, Integer slot) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return switch (slot) {
            case 1 -> post.getImage1();
            case 2 -> post.getImage2();
            default -> null;
        };
    }

}











