package com.example.EmployeeLeave.serviceimpl;


import com.example.EmployeeLeave.dto.EmployeeDTO;
import com.example.EmployeeLeave.entity.Department;
import com.example.EmployeeLeave.entity.Employee;
import com.example.EmployeeLeave.exception.ResourceNotFoundException;
import com.example.EmployeeLeave.repository.DepartmentRepository;
import com.example.EmployeeLeave.repository.EmployeeRepository;
import com.example.EmployeeLeave.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // Create Employee with Department
    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = mapToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return mapToDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        return mapToDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        existingEmployee.setEmployeeCode(employeeDTO.getEmployeeCode());
        existingEmployee.setName(employeeDTO.getName());
        existingEmployee.setEmail(employeeDTO.getEmail());
        existingEmployee.setPhone(employeeDTO.getPhone());
        existingEmployee.setDesignation(employeeDTO.getDesignation());
        existingEmployee.setJoiningDate(employeeDTO.getJoiningDate());
        existingEmployee.setLeaveBalance(employeeDTO.getLeaveBalance());
        existingEmployee.setRole(employeeDTO.getRole());
        existingEmployee.setStatus(employeeDTO.getStatus());

        // Update the department relationship if provided[cite: 2]
        if (employeeDTO.getDepartmentId() != null) {
            Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found with id: " + employeeDTO.getDepartmentId()));
            existingEmployee.setDepartment(department); // Using entity reference correctly[cite: 2]
        }

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return mapToDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }

    // --- Helper Methods for Mapping ---

    private Employee mapToEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setEmployeeCode(dto.getEmployeeCode());
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setDesignation(dto.getDesignation());
        employee.setJoiningDate(dto.getJoiningDate());
        employee.setLeaveBalance(dto.getLeaveBalance());
        employee.setRole(dto.getRole());
        employee.setStatus(dto.getStatus());

        // Per Story 7: Map the Department entity instead of a plain string[cite: 2]
        // Check for null BEFORE calling findById
        if (dto.getDepartmentId() == null) {
            throw new IllegalArgumentException("Department ID cannot be null when creating an employee.");
        }

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + dto.getDepartmentId()));

        return employee;
    }

    private EmployeeDTO mapToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setEmployeeCode(employee.getEmployeeCode());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setDesignation(employee.getDesignation());
        dto.setJoiningDate(employee.getJoiningDate());
        dto.setLeaveBalance(employee.getLeaveBalance());
        dto.setRole(employee.getRole());
        dto.setStatus(employee.getStatus());

        // Map department details back to DTO
        if (employee.getDepartment() != null) {
            dto.setDepartmentId(employee.getDepartment().getId());
            dto.setDepartmentName(employee.getDepartment().getDepartmentName());
        }

        return dto;
    }
}