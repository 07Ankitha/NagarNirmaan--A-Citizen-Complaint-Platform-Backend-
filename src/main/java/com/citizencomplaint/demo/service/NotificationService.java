package com.citizencomplaint.demo.service;

import com.citizencomplaint.demo.dto.NotificationDTO;
import com.citizencomplaint.demo.model.ComplaintStatus;
import com.citizencomplaint.demo.repository.NotificationRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<NotificationDTO> getNotifications() {
        List<ComplaintStatus> complaintStatuses = notificationRepository.findAll();
        return complaintStatuses.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private NotificationDTO mapToDTO(ComplaintStatus complaintStatus) {
        NotificationDTO dto = new NotificationDTO();
        dto.setId(complaintStatus.getId());
        dto.setComplaintId(complaintStatus.getComplaint().getId());
        dto.setComplaintTitle(complaintStatus.getComplaint().getTitle()); // Assuming Complaint has a title
        dto.setStatus(complaintStatus.getStatus().toString());
        dto.setRemarks(complaintStatus.getRemarks());
        dto.setUpdatedBy(complaintStatus.getUpdatedBy());
        dto.setUpdatedAt(complaintStatus.getUpdatedAt());
        return dto;
    }
}
