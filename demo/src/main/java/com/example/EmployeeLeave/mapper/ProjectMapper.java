package com.example.EmployeeLeave.mapper;
import com.example.EmployeeLeave.dto.ProjectDTO;
import com.example.EmployeeLeave.entity.Employee;
import com.example.EmployeeLeave.entity.Project;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {

    public ProjectDTO toDTO(Project entity) {
        if (entity == null) {
            return null;
        }

        ProjectDTO dto = new ProjectDTO();
        dto.setId(entity.getId());
        dto.setProjectName(entity.getProjectName());
        dto.setProjectCode(entity.getProjectCode());
        dto.setDescription(entity.getDescription());

        // Safely extract employee count and IDs
        if (entity.getEmployees() != null && !entity.getEmployees().isEmpty()) {
            dto.setEmployeeCount(entity.getEmployees().size());

            List<Long> employeeIds = entity.getEmployees().stream()
                    .map(Employee::getId)
                    .collect(Collectors.toList());
            dto.setEmployeeIds(employeeIds);
        } else {
            dto.setEmployeeCount(0);
            dto.setEmployeeIds(new ArrayList<>());
        }

        return dto;
    }

    public Project toEntity(ProjectDTO dto) {
        if (dto == null) {
            return null;
        }

        Project entity = new Project();
        entity.setId(dto.getId());
        entity.setProjectName(dto.getProjectName());
        entity.setProjectCode(dto.getProjectCode());
        entity.setDescription(dto.getDescription());

        // Note: Because Project has mappedBy = "projects", the Employee entity is the
        // "owning" side of this database relationship. To assign an employee to a project,
        // you usually need to fetch the Employee and add the Project to their list.
        // We do not map employeeIds back to entities here to avoid relationship confusion.

        return entity;
    }
}