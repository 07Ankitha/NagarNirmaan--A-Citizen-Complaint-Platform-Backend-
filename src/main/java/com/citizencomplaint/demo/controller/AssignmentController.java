package com.citizencomplaint.demo.controller;

import com.citizencomplaint.demo.dto.AssignmentDTO;
import com.citizencomplaint.demo.model.Assignment;
import com.citizencomplaint.demo.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/assignments")
@CrossOrigin(origins = "https://nagarnirmaan.onrender.com")
@RequiredArgsConstructor
public class AssignmentController {

    private static final Logger logger = LoggerFactory.getLogger(AssignmentController.class);
    private final AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<Assignment> createAssignment(@RequestBody AssignmentDTO assignmentDTO) {
        logger.info("Received request to create assignment: {}", assignmentDTO);
        try {
            Assignment assignment = assignmentService.createAssignment(assignmentDTO);
            logger.info("Assignment created successfully with ID: {}", assignment.getId());
            return new ResponseEntity<>(assignment, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid request to create assignment: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Error occurred while creating assignment:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/complaint/{complaintId}")
    public ResponseEntity<Assignment> getAssignmentByComplaintId(@PathVariable Long complaintId) {
        logger.info("Received request to get assignment for complaint ID: {}", complaintId);
        Optional<Assignment> assignmentOptional = assignmentService.getAssignmentByComplaintId(complaintId);

        if (assignmentOptional.isPresent()) {
            Assignment assignment = assignmentOptional.get();
            logger.info("Found assignment for complaint ID {}: {}", complaintId, assignment);
            return new ResponseEntity<>(assignment, HttpStatus.OK);
        } else {
            logger.warn("No assignment found for complaint ID: {}", complaintId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}