package com.example.EmployeeLeave.repository;

import com.example.EmployeeLeave.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    // Find projects assigned to an employee[cite: 2]
    List<Project> findByEmployeesId(Long employeeId);
}