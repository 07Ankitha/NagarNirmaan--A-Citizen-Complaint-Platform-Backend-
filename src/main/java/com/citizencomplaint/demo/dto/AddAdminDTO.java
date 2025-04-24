package com.citizencomplaint.demo.dto;

import lombok.Data;

@Data
public class AddAdminDTO {
    private String name;
    private String email;
    private String password;
}
