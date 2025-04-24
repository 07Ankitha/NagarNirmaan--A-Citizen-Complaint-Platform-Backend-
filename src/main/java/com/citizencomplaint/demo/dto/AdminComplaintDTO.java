package com.citizencomplaint.demo.dto;

import com.citizencomplaint.demo.model.Complaint.Status;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AdminComplaintDTO {
    private Long id;
    private String title;
    private String description;
    private String category;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String city;
    private Double latitude;
    private Double longitude;
    private String locationName;
    private String imageUrl;
    private Status status;
    private LocalDateTime createdAt;
    private Long userId; 
    private boolean isAssigned; 
}