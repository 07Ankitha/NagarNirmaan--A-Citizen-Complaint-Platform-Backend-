package com.citizencomplaint.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "feedback")
@Data
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "complaint_id", nullable = false)
    private String complaintId;

    @Column(name = "feedback_text")
    private String feedbackText;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public void setText(Object text) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setText'");
    }
}
