package com.citizencomplaint.demo.dto;

import java.math.BigDecimal;

public class BangaloreLocationDTO {

    private Long id;
    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;

    // ✅ Add this constructor
    public BangaloreLocationDTO(Long id, String name, BigDecimal latitude, BigDecimal longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}
