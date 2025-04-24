package com.citizencomplaint.demo.service;

import com.citizencomplaint.demo.dto.AdminComplaintDTO;
import com.citizencomplaint.demo.model.Complaint;
import com.citizencomplaint.demo.model.Complaint.Status;
import com.citizencomplaint.demo.repository.AdminComplaintRepository;
import com.citizencomplaint.demo.repository.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminComplaintServiceImpl implements AdminComplaint {

    private final AdminComplaintRepository complaintRepository;
    private final AssignmentRepository assignmentRepository;

    @Override
    public List<AdminComplaintDTO> getAllComplaints(String category, Status status) {
        List<Complaint> complaints;

        boolean allCategory = category == null || category.equalsIgnoreCase("ALL");
        boolean allStatus = status == null;

        if (allCategory && allStatus) {
            complaints = complaintRepository.findAll();
        } else if (!allCategory && allStatus) {
            complaints = complaintRepository.findByCategory(category);
        } else if (allCategory && !allStatus) {
            complaints = complaintRepository.findByStatus(status);
        } else {
            complaints = complaintRepository.findByCategoryAndStatus(category, status);
        }

        return complaints.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private AdminComplaintDTO mapToDTO(Complaint complaint) {
        return AdminComplaintDTO.builder()
                .id(complaint.getId())
                .title(complaint.getTitle())
                .description(complaint.getDescription())
                .category(complaint.getCategory())
                .name(complaint.getName())
                .email(complaint.getEmail())
                .address(complaint.getAddress())
                .phoneNumber(complaint.getPhoneNumber())
                .city(complaint.getCity())
                .latitude(complaint.getLatitude())
                .longitude(complaint.getLongitude())
                .locationName(complaint.getLocationName())
                .imageUrl(complaint.getImageUrl())
                .status(complaint.getStatus())
                .createdAt(complaint.getCreatedAt())
                .isAssigned(assignmentRepository.findByComplaintId(complaint.getId()).isPresent()) // Correct usage of isPresent()
                .build();
    }
}