package com.example.EmployeeLeave.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String jwt;
}