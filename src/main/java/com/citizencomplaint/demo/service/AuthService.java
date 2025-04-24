package com.citizencomplaint.demo.service;

import com.citizencomplaint.demo.dto.*;
import com.citizencomplaint.demo.model.User;

public interface AuthService {
    User register(RegisterRequest request); // Ensure this method is present in the interface

    String login(LoginRequest request);
}
