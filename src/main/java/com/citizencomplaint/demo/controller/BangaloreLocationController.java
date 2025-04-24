package com.citizencomplaint.demo.controller;

import com.citizencomplaint.demo.dto.BangaloreLocationDTO;
//import com.citizencomplaint.demo.model.BangaloreLocation;
import com.citizencomplaint.demo.service.BangaloreLocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
// @PreAuthorize or @Secured annotations here if needed
public class BangaloreLocationController {

    @Autowired
    private BangaloreLocationService service;

    @GetMapping
    public ResponseEntity<List<BangaloreLocationDTO>> getAll() {
    System.out.println("Received GET request for /api/locations");
    return ResponseEntity.ok(service.getAllLocations());
}
}
