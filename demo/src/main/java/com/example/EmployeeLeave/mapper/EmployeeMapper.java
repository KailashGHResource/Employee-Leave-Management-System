package com.example.EmployeeLeave.mapper;

import com.example.EmployeeLeave.dto.EmployeeDTO;
import com.example.EmployeeLeave.entity.Department;
import com.example.EmployeeLeave.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeDTO toDTO(Employee entity) {
        if (entity == null) {
            return null;
        }

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(entity.getId());
        dto.setEmployeeCode(entity.getEmployeeCode());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setDesignation(entity.getDesignation());
        dto.setJoiningDate(entity.getJoiningDate());
        dto.setLeaveBalance(entity.getLeaveBalance());
        dto.setRole(entity.getRole());
        dto.setStatus(entity.getStatus());

        // Handle the nested Department safely
        if (entity.getDepartment() != null) {
            dto.setDepartmentId(entity.getDepartment().getId());
            // Assuming the Department entity has a getDepartmentName() method based on your earlier repository code
            dto.setDepartmentName(entity.getDepartment().getDepartmentName());
        }

        return dto;
    }


    public Employee toEntity(EmployeeDTO dto) {
        if (dto == null) {
            return null;
        }

        Employee entity = new Employee();
        entity.setId(dto.getId());
        entity.setEmployeeCode(dto.getEmployeeCode());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setDesignation(dto.getDesignation());
        entity.setJoiningDate(dto.getJoiningDate());
        entity.setRole(dto.getRole());
        entity.setStatus(dto.getStatus());

        // Preserve the default value logic from your Entity if the DTO doesn't provide one
        if (dto.getLeaveBalance() != null) {
            entity.setLeaveBalance(dto.getLeaveBalance());
        } else {
            entity.setLeaveBalance(20);
        }

        // Map the department ID back to a Department object
        if (dto.getDepartmentId() != null) {
            Department department = new Department();
            department.setId(dto.getDepartmentId());
            entity.setDepartment(department);
        }

        // Note: Password, Address, Projects, and LeaveRequests are ignored here.
        // They should be handled separately in the Service layer when needed.

        return entity;
    }
}