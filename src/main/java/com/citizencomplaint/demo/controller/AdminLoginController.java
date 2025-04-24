package com.citizencomplaint.demo.controller;

import com.citizencomplaint.demo.dto.AdminLoginDTO;
import com.citizencomplaint.demo.model.AdminUser;
import com.citizencomplaint.demo.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminLoginController {

    @Autowired
    private AdminUserService adminUserService;

    @PostMapping("/admin-login")
    public ResponseEntity<?> login(@RequestBody AdminLoginDTO adminLoginDTO) {
        try {
            Optional<AdminUser> adminUserOptional = adminUserService.validateAdminLogin(adminLoginDTO);

            if (adminUserOptional.isPresent()) {
                AdminUser adminUser = adminUserOptional.get();

                // Example of a token, in real scenarios use JWT
                String token = UUID.randomUUID().toString();

                Map<String, Object> response = new HashMap<>();
                response.put("message", "Login successful");
                response.put("token", token);
                response.put("user", adminUser); // Make sure AdminUser is serializable

                return ResponseEntity.ok(response);
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Invalid credentials or access denied.");
                return ResponseEntity.status(401).body(error);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Optional: for debugging
            Map<String, String> error = new HashMap<>();
            error.put("error", "Something went wrong. Please try again.");
            return ResponseEntity.status(500).body(error);
        }
    }
}
