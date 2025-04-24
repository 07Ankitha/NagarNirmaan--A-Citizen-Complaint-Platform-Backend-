package com.citizencomplaint.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "assignments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Enable auto-generation
    @Column(name = "id") // Explicitly map the id column (optional but good practice)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "complaint_id", nullable = false)
    private Complaint complaint;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @Column(nullable = false, name = "start_date")
    private LocalDate startDate;

    @Column(nullable = false, name = "end_date")
    private LocalDate endDate;

    @Builder.Default
    @Column(name = "assigned_at", nullable = false, updatable = false)
    private LocalDateTime assignedAt = LocalDateTime.now();

}