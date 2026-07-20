package com.example.EmployeeLeave.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private Long id;
    private String houseNo;
    private String street;
    private String city;
    private String state;
    private String pincode;

    // We typically only include the ID of the employee if needed,
    // rather than the whole Employee object to avoid infinite loops.
    private Long employeeId;
}