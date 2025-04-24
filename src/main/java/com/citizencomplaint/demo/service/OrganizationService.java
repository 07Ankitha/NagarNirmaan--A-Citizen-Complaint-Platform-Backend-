package com.citizencomplaint.demo.service;

import com.citizencomplaint.demo.dto.OrganizationDTO;
import com.citizencomplaint.demo.model.Organization;
import com.citizencomplaint.demo.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationService.class);
    private final OrganizationRepository organizationRepository;

    public List<OrganizationDTO> getOrganizationsByCityAndCategory(String locationName, String categoryName) {
        logger.info("Fetching organizations for location name: '{}' and category name: '{}'", locationName, categoryName);
        // String trimmedLocationName = locationName != null ? locationName.trim() : null;
        // String trimmedCategoryName = categoryName != null ? categoryName.trim() : null;

        // logger.info("trimmedCategoryName", trimmedLocationName, trimmedCategoryName);

        // Calling the original repository method with trimmed values
        List<Organization> organizations = organizationRepository.findByCity_NameAndCategory_Name(locationName, categoryName);
        logger.info("Retrieved organizations from database: {}", organizations);
        return organizations.stream()
                .map(org -> new OrganizationDTO(org.getId(), org.getName()))
                .collect(Collectors.toList());
    }
}