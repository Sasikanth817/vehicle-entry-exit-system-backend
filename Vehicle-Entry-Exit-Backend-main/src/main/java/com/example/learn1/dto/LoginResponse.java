package com.example.learn1.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String message;
    private String role;
    private String token;
    private String email;
    private String empNumber;

    public LoginResponse(String message, String role, String token,String email, String empNumber) {
        this.message = message;
        this.role = role;
        this.token = token;
        this.email = email;
        this.empNumber = empNumber;
    }

    // Getters and Setters
}
