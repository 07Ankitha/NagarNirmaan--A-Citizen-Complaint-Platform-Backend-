package com.citizencomplaint.demo.service;

import com.citizencomplaint.demo.dto.FeedbackDTO;
import com.citizencomplaint.demo.model.Feedback;
import com.citizencomplaint.demo.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback submitFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setComplaintId(feedbackDTO.getComplaintId());
        feedback.setRating(feedbackDTO.getRating());
        feedback.setText(feedbackDTO.getText());
        return feedbackRepository.save(feedback);
    }
}
