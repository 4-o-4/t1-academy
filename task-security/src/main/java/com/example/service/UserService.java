package com.example.service;

import com.example.entity.User;
import com.example.request.LoginRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User createUser(LoginRequest loginRequest);
}
