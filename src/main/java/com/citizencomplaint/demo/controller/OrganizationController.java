package com.citizencomplaint.demo.controller;

import com.citizencomplaint.demo.dto.OrganizationDTO;
import com.citizencomplaint.demo.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    // Get organizations by city and category
    @GetMapping("/organizations")
    public ResponseEntity<List<OrganizationDTO>> getOrganizations(
            @RequestParam String city,
            @RequestParam String category) {
                System.out.println("organization");
                
        List<OrganizationDTO> organizations = organizationService.getOrganizationsByCityAndCategory(city, category);
        return ResponseEntity.ok(organizations);
    }
}
