package com.citizencomplaint.demo.service;

import com.citizencomplaint.demo.dto.*;
import com.citizencomplaint.demo.model.User;
import com.citizencomplaint.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // No encoding
        user.setRole(request.getRole() != null ? request.getRole() : "citizen");

        return userRepository.save(user);
    }

    @Override
    public String login(LoginRequest request) {
        // Add login logic
        return "Login Successful";
    }
}
