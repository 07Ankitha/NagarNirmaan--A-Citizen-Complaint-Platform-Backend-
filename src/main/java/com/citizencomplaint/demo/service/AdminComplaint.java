package com.citizencomplaint.demo.service;

import com.citizencomplaint.demo.dto.AdminComplaintDTO;
import com.citizencomplaint.demo.model.Complaint.Status;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface AdminComplaint {
    List<AdminComplaintDTO> getAllComplaints(String category, Status status);
}
