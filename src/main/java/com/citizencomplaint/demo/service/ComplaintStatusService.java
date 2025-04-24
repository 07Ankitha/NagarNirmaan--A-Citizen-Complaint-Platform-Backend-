package com.citizencomplaint.demo.service;

import com.citizencomplaint.demo.dto.ComplaintStatusDTO;
import com.citizencomplaint.demo.model.Complaint;
import com.citizencomplaint.demo.model.ComplaintStatus;
import com.citizencomplaint.demo.repository.ComplaintRepository;
import com.citizencomplaint.demo.repository.ComplaintStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ComplaintStatusService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private ComplaintStatusRepository complaintStatusRepository;

    public void updateComplaintStatus(Long complaintId, ComplaintStatusDTO dto, String updatedBy) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found with ID: " + complaintId));

        Complaint.Status statusEnum;
        try {
            statusEnum = Complaint.Status.valueOf(dto.getStatus().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status: " + dto.getStatus());
        }

        // Update status on complaint
        complaint.setStatus(statusEnum);
        complaintRepository.save(complaint);

        // Save status history
        ComplaintStatus complaintStatus = new ComplaintStatus();
        complaintStatus.setComplaint(complaint);
        complaintStatus.setStatus(statusEnum);
        complaintStatus.setRemarks(dto.getRemarks());
        complaintStatus.setUpdatedBy(updatedBy);
        complaintStatus.setUpdatedAt(LocalDateTime.now());

        complaintStatusRepository.save(complaintStatus);
    }
}
