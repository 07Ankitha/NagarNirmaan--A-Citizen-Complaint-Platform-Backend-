package com.citizencomplaint.demo.service;

import com.citizencomplaint.demo.dto.BangaloreLocationDTO;
import com.citizencomplaint.demo.model.BangaloreLocation;
import com.citizencomplaint.demo.repository.BangaloreLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BangaloreLocationService {

    @Autowired
    private BangaloreLocationRepository repository;

    public BangaloreLocationDTO createLocation(BangaloreLocation location) {
        if (repository.findByName(location.getName()).isPresent()) {
            throw new RuntimeException("Location with this name already exists");
        }
        BangaloreLocation saved = repository.save(location);
        return mapToDTO(saved);
    }

    public List<BangaloreLocationDTO> getAllLocations() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public BangaloreLocationDTO getLocationById(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Location not found"));
    }

    private BangaloreLocationDTO mapToDTO(BangaloreLocation loc) {
        return new BangaloreLocationDTO(loc.getId(), loc.getName(), loc.getLatitude(), loc.getLongitude());
    }
}