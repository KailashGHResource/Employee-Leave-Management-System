package com.example.EmployeeLeave.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull; // 1. Add this import

import java.time.LocalDate;

@Data
public class EmployeeDTO {
    private Long id;
    private String employeeCode;
    private String name;
    private String email;
    private String phone;
    private String designation;
    private LocalDate joiningDate;
    private Integer leaveBalance;
    private String role;
    private String status;

    // 2. Add the validation annotation here
    @NotNull(message = "Department ID is required")
    private Long departmentId;

    private String departmentName;
}