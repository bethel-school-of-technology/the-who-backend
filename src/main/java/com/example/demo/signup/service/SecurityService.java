package com.example.demo.signup.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}