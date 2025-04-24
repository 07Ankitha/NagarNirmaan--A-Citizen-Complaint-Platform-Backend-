package com.citizencomplaint.demo.controller;

import com.citizencomplaint.demo.dto.*;
import com.citizencomplaint.demo.model.User;
import com.citizencomplaint.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.citizencomplaint.demo.repository.*;

import org.springframework.http.HttpStatus;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
    try {
        User user = authService.register(request);
        return ResponseEntity.ok(user);
    } catch (Exception e) {
        // Log the exception (optional but good practice)
        e.printStackTrace();

        // You can customize the error message as needed
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Registration failed: " + e.getMessage());
    }
}

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request) {
        // Assuming login() method will return a token
        String token = authService.login(request);

        // Prepare the response map
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);

        // If you need user details (like role), add it to the response
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent()) {
            response.put("user", user.get());
        }

        // Return the response as JSON
        return ResponseEntity.ok(response);
    }

}
