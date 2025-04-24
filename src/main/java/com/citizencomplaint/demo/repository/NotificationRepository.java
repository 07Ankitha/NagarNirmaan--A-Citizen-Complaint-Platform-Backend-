package com.citizencomplaint.demo.repository;

import com.citizencomplaint.demo.model.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<ComplaintStatus, Long> {
}
