package com.hartcircle.admin.services;

import com.hartcircle.admin.dto.PostViewDto;
import com.hartcircle.admin.dto.UserSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PostClient {

    @Value("${post.service.url}")
    private String postServiceUrl;

    private final RestTemplate restTemplate;
    private final JwtClient jwtClient;

    public PostClient(RestTemplate restTemplate, JwtClient jwtClient) {
        this.restTemplate = restTemplate;
        this.jwtClient = jwtClient;
    }

    public List<PostViewDto> getPosts(String nic) {
        try {
            String url = postServiceUrl + "/api/v1/post/admin/getinforbyNIC/" + nic;

            // This part is important.post-service JWTFilter identify this as authorized request  with header.
            String token = jwtClient.generateToken(nic);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<List<PostViewDto>> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            entity,
                            new ParameterizedTypeReference<List<PostViewDto>>() {}
                    );

            return response.getBody();
        } catch (Exception e) {
            System.out.println("Failed to fetch posts: " + e.getMessage());
            return List.of();
        }
    }


}
