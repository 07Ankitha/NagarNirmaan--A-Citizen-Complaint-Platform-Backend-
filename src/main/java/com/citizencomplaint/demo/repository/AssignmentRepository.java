package com.citizencomplaint.demo.repository;

import com.citizencomplaint.demo.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    Optional<Assignment> findByComplaintId(Long complaintId);
    // You can add custom query methods here if needed

    @Query("SELECT a FROM Assignment a WHERE a.startDate <= :today AND a.complaint.status = 'PENDING' AND a.complaint IS NOT NULL")
    List<Assignment> findAssignmentsReadyForInProgress(@Param("today") LocalDate today);

    @Query("SELECT a FROM Assignment a WHERE a.endDate <= :today AND a.complaint.status = 'IN_PROGRESS' AND a.complaint IS NOT NULL")
    List<Assignment> findAssignmentsReadyForResolved(@Param("today") LocalDate today);
}