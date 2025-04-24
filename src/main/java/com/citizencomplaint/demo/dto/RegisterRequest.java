package com.citizencomplaint.demo.dto;


import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String name;
    private String password;
    private String role;

    // Getters
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // Setters if needed
    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
