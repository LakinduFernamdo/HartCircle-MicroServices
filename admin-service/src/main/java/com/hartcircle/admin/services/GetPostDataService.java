package com.hartcircle.admin.services;

import com.hartcircle.admin.dto.PostViewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostDataService {
    @Autowired
    private PostClient postClient;

    public List<PostViewDto> getPostinforByNIC(String nic) {
        return postClient.getPosts(nic);
    }


}
