package com.citizencomplaint.demo.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComplaintDto {
    private Long id;
    private String title;
    private String description;
    private String category;
    private String status;
    private LocalDateTime createdAt;
    private String locationName; // Add this field
    private Double latitude;  // Add latitude
    private Double longitude; // Add longitude
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String city;
    private String imageUrl;
}
