package com.citizencomplaint.demo.repository;

import com.citizencomplaint.demo.model.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintStatusRepository extends JpaRepository<ComplaintStatus, Long> {
}
