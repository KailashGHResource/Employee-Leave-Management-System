package com.example.EmployeeLeave.dto;

import lombok.Data;
import java.util.List;

@Data
public class ProjectDTO {
    private Long id;
    private String projectName;
    private String projectCode;
    private String description;

    // Option 1: Just provide the count of employees assigned
    private int employeeCount;

    // Option 2: Provide a list of employee IDs if you need to know who is working on it
    private List<Long> employeeIds;
}