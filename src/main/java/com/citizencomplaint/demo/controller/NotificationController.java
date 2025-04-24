package com.citizencomplaint.demo.controller;

import com.citizencomplaint.demo.dto.NotificationDTO;
import com.citizencomplaint.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "http://localhost:3000") 
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    
    @GetMapping
    public ResponseEntity<Object> getNotifications() {
        try {
            List<NotificationDTO> notifications = notificationService.getNotifications();
            return ResponseEntity.ok(notifications); 
        } catch (Exception e) {
            
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to fetch notifications: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
