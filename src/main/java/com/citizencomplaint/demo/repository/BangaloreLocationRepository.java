package com.citizencomplaint.demo.repository;

import com.citizencomplaint.demo.model.BangaloreLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BangaloreLocationRepository extends JpaRepository<BangaloreLocation, Long> {
    Optional<BangaloreLocation> findByName(String name);
}
