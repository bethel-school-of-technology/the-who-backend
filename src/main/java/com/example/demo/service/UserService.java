package com.example.demo.service;

import java.util.Optional;

import com.example.demo.signup.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}