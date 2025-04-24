package com.citizencomplaint.demo.dto;

import lombok.Data;

@Data
public class FeedbackDTO {
    private String complaintId;
    private String feedbackText;
    private Integer rating;
    public Object getText() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getText'");
    }
}
