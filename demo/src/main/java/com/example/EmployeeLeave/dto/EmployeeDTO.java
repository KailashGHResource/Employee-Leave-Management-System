package com.example.EmployeeLeave.dto;

import lombok.Data;
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

    // You can include specific IDs or nested DTOs here
    private Long departmentId;
    private String departmentName; // Optional: include if you want to show the name

    // If you need address/project details, consider creating AddressDTO and ProjectDTO
    // and including them here as:
    // private AddressDTO address;
    // private List<Long> projectIds;
}