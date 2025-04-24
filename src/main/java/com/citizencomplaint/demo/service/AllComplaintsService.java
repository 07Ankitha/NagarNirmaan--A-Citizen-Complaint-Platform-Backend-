
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
public class AllComplaintsService {

    private final ComplaintRepository complaintRepository;

    public List<ComplaintDto> getUserComplaints(User user) {
        List<Complaint> complaints = complaintRepository.findByUser(user);
        return complaints.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
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
                .name(complaint.getName())
                .email(complaint.getEmail())
                .address(complaint.getAddress())
                .phoneNumber(complaint.getPhoneNumber())
                .city(complaint.getCity())
                .imageUrl(complaint.getImageUrl())
                .build();
    }
}
