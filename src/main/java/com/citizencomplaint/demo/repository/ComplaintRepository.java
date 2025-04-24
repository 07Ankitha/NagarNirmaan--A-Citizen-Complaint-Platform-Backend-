package com.citizencomplaint.demo.repository;

import com.citizencomplaint.demo.model.Complaint;
import com.citizencomplaint.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByUser(User user);
}
