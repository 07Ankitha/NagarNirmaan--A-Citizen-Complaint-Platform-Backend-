package com.citizencomplaint.demo.repository;

import com.citizencomplaint.demo.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
