package com.example.demo.signup.service;

import com.example.demo.signup.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}