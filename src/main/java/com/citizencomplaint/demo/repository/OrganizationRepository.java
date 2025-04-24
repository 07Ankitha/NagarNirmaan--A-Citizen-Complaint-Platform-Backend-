package com.citizencomplaint.demo.repository;

import com.citizencomplaint.demo.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    List<Organization> findByCity_NameAndCategory_Name(String cityName, String categoryName);
}