
package com.citizencomplaint.demo.controller;

import com.citizencomplaint.demo.dto.ComplaintDto;
//import com.citizencomplaint.demo.model.Complaint;
import com.citizencomplaint.demo.model.User;
import com.citizencomplaint.demo.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
public class AllComplaintsController {

    private final ComplaintService complaintService;

   

    @GetMapping("/user")  
    public ResponseEntity<List<ComplaintDto>> getUserComplaints(@AuthenticationPrincipal User user) {
        List<ComplaintDto> complaints = complaintService.getUserComplaints(user);
        return ResponseEntity.ok(complaints);
    }
}
