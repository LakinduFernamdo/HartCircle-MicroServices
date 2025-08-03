package com.hartcircle.post.repo;


import com.hartcircle.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findByItemTypeIgnoreCase(String itemType);
    Optional<Post> findByPostID(Integer postID);



}
