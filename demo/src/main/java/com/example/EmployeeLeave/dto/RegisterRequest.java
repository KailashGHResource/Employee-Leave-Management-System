package com.example.EmployeeLeave.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String employeeCode;
    private String name;
    private String email;
    private String password;
    private String designation;
    private String phone;

    // The role must be sent during registration (e.g., "ROLE_EMPLOYEE" or "ROLE_ADMIN")
    private String role;
}