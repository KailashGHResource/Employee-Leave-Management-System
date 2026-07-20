package com.example.EmployeeLeave.mapper;

import com.example.EmployeeLeave.dto.DepartmentDTO;
import com.example.EmployeeLeave.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    /**
     * Converts a Department Entity to a DepartmentDTO.
     */
    public DepartmentDTO toDTO(Department entity) {
        if (entity == null) {
            return null;
        }

        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(entity.getId());
        dto.setDepartmentName(entity.getDepartmentName());
        dto.setDescription(entity.getDescription());

        // Safely calculate the employee count
        if (entity.getEmployees() != null) {
            dto.setEmployeeCount(entity.getEmployees().size());
        } else {
            dto.setEmployeeCount(0);
        }

        return dto;
    }

    /**
     * Converts a DepartmentDTO to a Department Entity.
     */
    public Department toEntity(DepartmentDTO dto) {
        if (dto == null) {
            return null;
        }

        Department entity = new Department();
        entity.setId(dto.getId());
        entity.setDepartmentName(dto.getDepartmentName());
        entity.setDescription(dto.getDescription());

        // Note: We do NOT map employeeCount back to the entity.
        // The list of employees should be managed through the Employee entity's side
        // (since it holds the foreign key via @JoinColumn).

        return entity;
    }
}