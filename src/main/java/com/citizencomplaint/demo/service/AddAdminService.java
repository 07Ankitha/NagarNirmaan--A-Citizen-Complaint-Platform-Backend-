package com.citizencomplaint.demo.service;

import com.citizencomplaint.demo.dto.AddAdminDTO;
import com.citizencomplaint.demo.model.AdminUser;
import com.citizencomplaint.demo.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AddAdminService {

    @Autowired
    private AdminUserRepository adminUserRepository;

    public void createAdmin(AddAdminDTO dto) {
        if (adminUserRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        AdminUser newAdmin = new AdminUser();
        newAdmin.setName(dto.getName());
        newAdmin.setEmail(dto.getEmail());
        newAdmin.setPassword(dto.getPassword()); // Encrypt in production
        newAdmin.setRole("admin");
        newAdmin.setCreatedAt(LocalDateTime.now());
        newAdmin.setUpdatedAt(LocalDateTime.now());

        adminUserRepository.save(newAdmin);
    }
}
