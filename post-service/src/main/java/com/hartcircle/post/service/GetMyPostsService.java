package com.hartcircle.post.service;

import com.hartcircle.post.dto.PostViewDto;
import com.hartcircle.post.entity.Post;
import com.hartcircle.post.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
                                     //This Service Render My own Posts by compare userNic
@Service
public class GetMyPostsService {

    @Autowired
    private PostRepository postRepository;

    public List<PostViewDto> renderMyownPosts(String userNic) {
        List<Post> myposts=postRepository.findByUserNIC(userNic);
        return myposts.stream().map(
                post -> {
                    PostViewDto dto = new PostViewDto();
                    dto.setStartDate(post.getStartDate());
                    dto.setStartTime(post.getStartTime());
                    dto.setEndTime(post.getEndTime());
                    dto.setEndDate(post.getEndDate());
                    dto.setBidLimit(post.getBidLimit());
                    dto.setItemType(post.getItemType());
                    dto.setDescription(post.getDescription());
                    dto.setImage1Url("http://localhost:8081/api/v1/post/image/" + post.getPostID() + "/1");
                    dto.setImage2Url("http://localhost:8081/api/v1/post/image/" + post.getPostID() + "/2");
                    return dto;
                }).collect(Collectors.toList());
    }
}




