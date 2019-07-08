package com.example.demo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.demo.model.Post;
import com.example.demo.signup.model.User;

public interface PostService {

    Optional<Post> findForId(Long id);

    Post save(Post post);

    /**
     * Finds a {@link Page) of {@link Post} of provided user ordered by date
     */
    Page<Post> findByUserOrderedByDatePageable(User user, int page);

    /**
     * Finds a {@link Page) of all {@link Post} ordered by date
     */
    Page<Post> findAllOrderedByDatePageable(int page);

    void delete(Post post);
}

