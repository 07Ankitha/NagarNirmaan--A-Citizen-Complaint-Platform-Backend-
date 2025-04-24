package com.citizencomplaint.demo.service;

import com.citizencomplaint.demo.model.Assignment;
import com.citizencomplaint.demo.model.Complaint;
import com.citizencomplaint.demo.model.Complaint.Status;
import com.citizencomplaint.demo.model.ComplaintStatus;
import com.citizencomplaint.demo.repository.AssignmentRepository;
import com.citizencomplaint.demo.repository.ComplaintRepository;
import com.citizencomplaint.demo.repository.ComplaintStatusRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AutoComplaintStatusUpdateService {

    private final AssignmentRepository assignmentRepository;
    private final ComplaintRepository complaintRepository;
    private final ComplaintStatusRepository complaintStatusRepository;

    public AutoComplaintStatusUpdateService(AssignmentRepository assignmentRepository, ComplaintRepository complaintRepository, ComplaintStatusRepository complaintStatusRepository) {
        this.assignmentRepository = assignmentRepository;
        this.complaintRepository = complaintRepository;
        this.complaintStatusRepository = complaintStatusRepository;
    }

    @Scheduled(cron = "0 * * * * ?") 
    @Transactional
    public void updateComplaintStatusesBasedOnAssignmentDates() {
        LocalDate today = LocalDate.now();

        // Update to IN_PROGRESS
        List<Assignment> assignmentsForInProgress = assignmentRepository.findAssignmentsReadyForInProgress(today);
        for (Assignment assignment : assignmentsForInProgress) {
            Complaint complaint = assignment.getComplaint();
            if (complaint.getStatus() == Status.PENDING) {
                complaint.setStatus(Status.IN_PROGRESS);
                complaintRepository.save(complaint);
                createComplaintStatusLog(complaint, Status.IN_PROGRESS, "Updated automatically based on assignment start date", "System");
            }
        }

        // Update to RESOLVED
        List<Assignment> assignmentsForResolved = assignmentRepository.findAssignmentsReadyForResolved(today);
        for (Assignment assignment : assignmentsForResolved) {
            Complaint complaint = assignment.getComplaint();
            if (complaint.getStatus() == Status.IN_PROGRESS) {
                complaint.setStatus(Status.RESOLVED);
                complaintRepository.save(complaint);
                createComplaintStatusLog(complaint, Status.RESOLVED, "Updated automatically based on assignment end date", "System");
            }
        }
    }

    private void createComplaintStatusLog(Complaint complaint, Status newStatus, String remarks, String updatedBy) {
        ComplaintStatus statusLog = new ComplaintStatus();
        statusLog.setComplaint(complaint);
        statusLog.setStatus(newStatus);
        statusLog.setRemarks(remarks);
        statusLog.setUpdatedBy(updatedBy);
        statusLog.setUpdatedAt(LocalDateTime.now());
        complaintStatusRepository.save(statusLog);
    }
}