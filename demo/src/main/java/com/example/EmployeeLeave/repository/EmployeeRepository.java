package com.example.EmployeeLeave.repository;


import com.example.EmployeeLeave.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Find employees by department
    List<Employee> findByDepartmentId(Long departmentId);

    // Find employees working on a project
    List<Employee> findByProjectsId(Long projectId);
}