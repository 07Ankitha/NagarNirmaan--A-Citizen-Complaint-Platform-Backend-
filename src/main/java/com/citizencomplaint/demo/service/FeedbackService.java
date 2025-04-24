package com.citizencomplaint.demo.service;

import com.citizencomplaint.demo.dto.FeedbackDTO;
import com.citizencomplaint.demo.model.Feedback;

import java.util.List;

public interface FeedbackService {
    List<Feedback> getAllFeedback();
    Feedback submitFeedback(FeedbackDTO feedbackDTO);

    
}

