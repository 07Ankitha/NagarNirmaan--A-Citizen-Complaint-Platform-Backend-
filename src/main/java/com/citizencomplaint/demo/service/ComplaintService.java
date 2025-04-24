package com.citizencomplaint.demo.service;

import com.citizencomplaint.demo.dto.ComplaintDto;
import com.citizencomplaint.demo.model.Complaint;
import com.citizencomplaint.demo.model.User;
import com.citizencomplaint.demo.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    public List<ComplaintDto> getUserComplaints(User user) {
        return complaintRepository.findByUser(user)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ComplaintDto saveComplaint(Complaint complaint) {
        Complaint savedComplaint = complaintRepository.save(complaint);
        return toDto(savedComplaint);
    }

    public Complaint getComplaintById(Long id) {
        return complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + id));
    }

    private ComplaintDto toDto(Complaint complaint) {
        return ComplaintDto.builder()
                .id(complaint.getId())
                .title(complaint.getTitle())
                .description(complaint.getDescription())
                .category(complaint.getCategory())
                .status(complaint.getStatus().name())
                .createdAt(complaint.getCreatedAt())
                .locationName(complaint.getLocationName())
                .latitude(complaint.getLatitude())
                .longitude(complaint.getLongitude())
                .name(complaint.getName())  // Include name in DTO
                .email(complaint.getEmail()) // Include email
                .address(complaint.getAddress())
                .phoneNumber(complaint.getPhoneNumber())
                .city(complaint.getCity())
                .imageUrl(complaint.getImageUrl())
                .build();
    }
}