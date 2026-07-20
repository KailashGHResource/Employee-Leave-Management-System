package com.example.EmployeeLeave.dto;

import lombok.Data;

@Data
public class DepartmentDTO {
    private Long id;
    private String departmentName;
    private String description;

    // If you need to know how many employees are in the department,
    // you can include a count instead of the full list.
    private int employeeCount;
}