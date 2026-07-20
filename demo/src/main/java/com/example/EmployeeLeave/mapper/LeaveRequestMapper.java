package com.example.EmployeeLeave.mapper;

import com.example.EmployeeLeave.dto.LeaveRequestDTO;
import com.example.EmployeeLeave.entity.Employee;
import com.example.EmployeeLeave.entity.LeaveRequest;
import org.springframework.stereotype.Component;

@Component
public class LeaveRequestMapper {

    /**
     * Converts a LeaveRequest Entity to a LeaveRequestDTO.
     */
    public LeaveRequestDTO toDTO(LeaveRequest entity) {
        if (entity == null) {
            return null;
        }

        LeaveRequestDTO dto = new LeaveRequestDTO();
        dto.setId(entity.getId());
        dto.setLeaveType(entity.getLeaveType());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setNumberOfDays(entity.getNumberOfDays());
        dto.setReason(entity.getReason());
        dto.setStatus(entity.getStatus());
        dto.setApprovedDate(entity.getApprovedDate());
        dto.setRemarks(entity.getRemarks());

        // Safely extract Employee details
        if (entity.getEmployee() != null) {
            dto.setEmployeeId(entity.getEmployee().getId());
            dto.setEmployeeName(entity.getEmployee().getName());
        }

        return dto;
    }

    /**
     * Converts a LeaveRequestDTO to a LeaveRequest Entity.
     */
    public LeaveRequest toEntity(LeaveRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        LeaveRequest entity = new LeaveRequest();
        entity.setId(dto.getId());
        entity.setLeaveType(dto.getLeaveType());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setNumberOfDays(dto.getNumberOfDays());
        entity.setReason(dto.getReason());
        entity.setStatus(dto.getStatus());
        entity.setApprovedDate(dto.getApprovedDate());
        entity.setRemarks(dto.getRemarks());

        // Map the employeeId back to an Employee entity reference
        if (dto.getEmployeeId() != null) {
            Employee employee = new Employee();
            employee.setId(dto.getEmployeeId());
            entity.setEmployee(employee);
        }

        return entity;
    }
}