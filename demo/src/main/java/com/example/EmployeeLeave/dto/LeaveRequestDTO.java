package com.example.EmployeeLeave.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class LeaveRequestDTO {
    private Long id;
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer numberOfDays;
    private String reason;
    private String status;
    private LocalDateTime approvedDate;
    private String remarks;

    // Use IDs to represent the relationship
    private Long employeeId;

    // Optional: Include the name if you want to display it in a list of requests
    private String employeeName;
}
