package com.example.EmployeeLeave.repository;

import com.example.EmployeeLeave.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    // Find leave requests by employee
    List<LeaveRequest> findByEmployeeId(Long employeeId);
}