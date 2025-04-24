package com.citizencomplaint.demo.dto;


import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;

    // Getters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters if needed
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

