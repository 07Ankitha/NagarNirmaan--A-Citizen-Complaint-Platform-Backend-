package com.citizencomplaint.demo.controller;

import com.citizencomplaint.demo.dto.ComplaintDto;
import com.citizencomplaint.demo.model.Complaint;
import com.citizencomplaint.demo.model.User;
import com.citizencomplaint.demo.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService complaintService;

    // Configuration for where to store uploaded images
    private final String UPLOAD_DIR = "uploads"; // Make this a constant

    @PostMapping
public ResponseEntity<ComplaintDto> createComplaint(
        @RequestParam("name") String name,
        @RequestParam("email") String email,
        @RequestParam("address") String address,
        @RequestParam("phoneNumber") String phoneNumber,
        @RequestParam("title") String title,
        @RequestParam("description") String description,
        @RequestParam("category") String category,
        @RequestParam("city") String city,
        @RequestParam(value = "latitude", required = false) Double latitude,
        @RequestParam(value = "longitude", required = false) Double longitude,
        @RequestParam(value = "locationName", required = false) String locationName,
        @RequestParam(value = "image", required = false) MultipartFile image,
        @AuthenticationPrincipal User user) throws IOException {

    Complaint complaint = new Complaint();
    complaint.setUser(user);
    complaint.setName(name);
    complaint.setEmail(email);
    complaint.setAddress(address);
    complaint.setPhoneNumber(phoneNumber);
    complaint.setTitle(title);
    complaint.setDescription(description);
    complaint.setCategory(category);
    complaint.setCity(city);
    complaint.setLatitude(latitude);
    complaint.setLongitude(longitude);
    complaint.setLocationName(locationName);

    if (image != null && !image.isEmpty()) {
        String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR, fileName);

        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            complaint.setImageUrl(filePath.toString().replace("\\", "/")); // Convert backslashes to forward slashes
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save image", e);
        }
    }

    ComplaintDto savedComplaint = complaintService.saveComplaint(complaint);
    return new ResponseEntity<>(savedComplaint, HttpStatus.CREATED);
}


    @GetMapping("/{id}")
    public ResponseEntity<Complaint> getComplaintById(@PathVariable Long id) {
        Complaint complaint = complaintService.getComplaintById(id);
        return ResponseEntity.ok(complaint);
    }

}
