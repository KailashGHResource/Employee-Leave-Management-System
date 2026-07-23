package com.example.EmployeeLeave.repository;


import com.example.EmployeeLeave.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Find employees by department
    List<Employee> findByDepartmentId(Long departmentId);

    // Find employees working on a project
    List<Employee> findByProjectsId(Long projectId);

    Optional<Employee> findByEmail(String email);

    // 2. Added this method because your AuthController needs it
    // to check if an email is already taken during Registration!
    boolean existsByEmail(String email);
}