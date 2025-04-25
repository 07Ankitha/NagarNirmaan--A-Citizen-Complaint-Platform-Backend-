package com.citizencomplaint.demo.controller;

import com.citizencomplaint.demo.dto.AdminComplaintDTO;
import com.citizencomplaint.demo.model.Complaint.Status;
import com.citizencomplaint.demo.service.AdminComplaint;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://nagarnirmaan.onrender.com")
@RequiredArgsConstructor
public class AdminComplaintController {

    private final AdminComplaint complaintService;

    // Admin - Get all complaints with optional filtering
    @GetMapping("/admin/complaints")
    public ResponseEntity<List<AdminComplaintDTO>> getAllComplaints(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Status status) {
        try {
            List<AdminComplaintDTO> complaints = complaintService.getAllComplaints(category, status);
            return ResponseEntity.ok(complaints);
        } catch (Exception e) {
            // Log the error for debugging purposes
            System.err.println("Error fetching all complaints: " + e.getMessage());
            // Optionally, you might want to log the full stack trace:
            // e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to retrieve complaints. Please check the server logs.",
                    e // Include the exception for more context if needed
            );
        }
    }
}