package com.citizencomplaint.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String category;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String city; // Added city
    private Double latitude;
    private Double longitude;
    private String locationName;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public enum Status {
        PENDING, IN_PROGRESS, RESOLVED
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        if (status == null) {
            status = Status.PENDING;
        }
    }
}
