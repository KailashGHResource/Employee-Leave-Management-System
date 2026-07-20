package com.example.EmployeeLeave.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String houseNo;
    private String street;
    private String city;
    private String state;
    private String pincode;
    // Bidirectional One-to-One mapping back to the employee
    @OneToOne(mappedBy = "address")
    private Employee employee;
}