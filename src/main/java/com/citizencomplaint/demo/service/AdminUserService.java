package com.citizencomplaint.demo.service;

import com.citizencomplaint.demo.dto.AdminLoginDTO;
import com.citizencomplaint.demo.model.AdminUser;
import com.citizencomplaint.demo.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminUserService {
    
    @Autowired
    private AdminUserRepository adminUserRepository;
    
    public Optional<AdminUser> validateAdminLogin(AdminLoginDTO adminLoginDTO) {
        Optional<AdminUser> adminUser = adminUserRepository.findByEmail(adminLoginDTO.getEmail());
        
        // You can add password validation here (e.g., bcrypt check)
        if (adminUser.isPresent() && adminUser.get().getPassword().equals(adminLoginDTO.getPassword())) {
            return adminUser;
        }
        
        return Optional.empty();
    }
}
