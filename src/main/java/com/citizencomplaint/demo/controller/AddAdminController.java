package com.citizencomplaint.demo.controller;

import com.citizencomplaint.demo.dto.AddAdminDTO;
import com.citizencomplaint.demo.model.AdminUser;
import com.citizencomplaint.demo.repository.AdminUserRepository;
import com.citizencomplaint.demo.service.AddAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/users")
@CrossOrigin(origins = "http://localhost:3000")
public class AddAdminController {

    @Autowired
    private AddAdminService addAdminService;

    @Autowired
    private AdminUserRepository adminUserRepository;

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addAdminUser(
            @RequestBody AddAdminDTO newAdmin,
            @RequestHeader("Logged-In-User") String currentUserEmail) {

        AdminUser requestingUser = adminUserRepository.findByEmail(currentUserEmail)
                .orElse(null);

        if (requestingUser == null || !"admin".equalsIgnoreCase(requestingUser.getRole())) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Only ADMIN users can create a new admin.");
            return ResponseEntity.status(403).body(errorResponse);
        }

        try {
            addAdminService.createAdmin(newAdmin);
            Map<String, String> successResponse = new HashMap<>();
            successResponse.put("message", "Admin user created successfully.");
            return ResponseEntity.ok(successResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to create admin user: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
