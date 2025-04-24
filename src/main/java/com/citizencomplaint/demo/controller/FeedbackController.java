package com.citizencomplaint.demo.controller;

import com.citizencomplaint.demo.dto.FeedbackDTO;
import com.citizencomplaint.demo.model.Feedback;
import com.citizencomplaint.demo.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = "http://localhost:3000")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<Feedback> submitFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        Feedback savedFeedback = feedbackService.submitFeedback(feedbackDTO);
        return new ResponseEntity<>(savedFeedback, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        return ResponseEntity.ok(feedbackService.getAllFeedback());
    }
}
