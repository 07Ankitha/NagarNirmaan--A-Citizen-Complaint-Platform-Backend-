package com.citizencomplaint.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentDTO {
    private Long complaintId;
    private Long organizationId;
    private LocalDate startDate;
    private LocalDate endDate;
}