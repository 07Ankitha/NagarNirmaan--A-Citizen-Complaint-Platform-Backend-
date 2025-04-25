package com.citizencomplaint.demo.controller;

import com.citizencomplaint.demo.dto.ComplaintStatusDTO;
import com.citizencomplaint.demo.model.AdminUser;
import com.citizencomplaint.demo.repository.AdminUserRepository;
import com.citizencomplaint.demo.service.ComplaintStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/complaints")
@CrossOrigin(origins = "https://nagarnirmaan.onrender.com")
public class ComplaintStatusController {

    @Autowired
    private ComplaintStatusService complaintStatusService;

    @Autowired
    private AdminUserRepository adminUserRepository;

    @PutMapping("/{complaintId}/status")
    public ResponseEntity<Map<String, String>> updateComplaintStatus(
            @PathVariable Long complaintId,
            @RequestBody ComplaintStatusDTO request
    ) {
        try {
            AdminUser adminUser = adminUserRepository.findById(request.getAdminId())
                    .orElseThrow(() -> new RuntimeException("No admin user found"));

            String updatedBy = adminUser.getName(); 

            complaintStatusService.updateComplaintStatus(complaintId, request, updatedBy);

            // Success response
            Map<String, String> response = new HashMap<>();
            response.put("message", "Complaint status updated successfully");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // Error response
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to update complaint status: " + e.getMessage());

            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
