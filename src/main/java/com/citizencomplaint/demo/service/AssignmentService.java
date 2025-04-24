package com.citizencomplaint.demo.service;

import com.citizencomplaint.demo.dto.AssignmentDTO;
import com.citizencomplaint.demo.model.Assignment;
import com.citizencomplaint.demo.model.Complaint;
import com.citizencomplaint.demo.model.Organization;
import com.citizencomplaint.demo.repository.AssignmentRepository;
import com.citizencomplaint.demo.repository.ComplaintRepository;
import com.citizencomplaint.demo.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private static final Logger logger = LoggerFactory.getLogger(AssignmentService.class);
    private final AssignmentRepository assignmentRepository;
    private final ComplaintRepository complaintRepository;
    private final OrganizationRepository organizationRepository;

    @Transactional
    public Assignment createAssignment(AssignmentDTO assignmentDTO) {
        logger.info("Attempting to create assignment: {}", assignmentDTO);
        try {
            Optional<Complaint> complaintOptional = complaintRepository.findById(assignmentDTO.getComplaintId());
            Optional<Organization> organizationOptional = organizationRepository
                    .findById(assignmentDTO.getOrganizationId());

                    System.out.println("this is the optional complaint"+ complaintOptional);
                    System.out.println("this is the organizationOptional"+ organizationOptional);

            if (complaintOptional.isEmpty()) {
                String errorMessage = "Complaint not found with ID: " + assignmentDTO.getComplaintId();
                logger.warn(errorMessage);
                throw new IllegalArgumentException(errorMessage);
            }
            if (organizationOptional.isEmpty()) {
                String errorMessage = "Organization not found with ID: " + assignmentDTO.getOrganizationId();
                logger.warn(errorMessage);
                throw new IllegalArgumentException(errorMessage);
            }

            Complaint complaint = complaintOptional.get();
            Organization organization = organizationOptional.get();

            System.out.println("this is the complaint"+complaint);
            System.out.println("this is the organization"+organization);

            

            Assignment assignment = Assignment.builder()
                    .complaint(complaint)
                    .organization(organization)
                    .startDate(assignmentDTO.getStartDate())
                    .endDate(assignmentDTO.getEndDate())
                    .build();

            System.out.println("this is the assignment"+assignment);

            Assignment savedAssignment = assignmentRepository.save(assignment);
            logger.info("Assignment created successfully with ID: {}", savedAssignment.getId());
            return savedAssignment;

        } catch (IllegalArgumentException e) {
            // Logged already
            throw e;
        } catch (Exception e) {
            System.out.println("Error occurred while creating assignment:" + e);
            throw new RuntimeException("Failed to create assignment", e); // Re-throw for
            // the controller to handle
        }
    }

    public Optional<Assignment> getAssignmentByComplaintId(Long complaintId) {
        logger.info("Fetching assignment for complaint ID: {}", complaintId);
        return assignmentRepository.findByComplaintId(complaintId);
    }
}