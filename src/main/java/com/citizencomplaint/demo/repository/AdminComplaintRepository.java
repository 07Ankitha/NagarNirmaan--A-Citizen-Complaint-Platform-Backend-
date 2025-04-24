package com.citizencomplaint.demo.repository;

import com.citizencomplaint.demo.model.Complaint;
import com.citizencomplaint.demo.model.Complaint.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByCategory(String category);
    List<Complaint> findByStatus(Status status);
    List<Complaint> findByCategoryAndStatus(String category, Status status);
}
