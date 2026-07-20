package com.example.EmployeeLeave.serviceimpl;


import com.example.EmployeeLeave.dto.DepartmentDTO;
import com.example.EmployeeLeave.entity.Department;
import com.example.EmployeeLeave.mapper.DepartmentMapper;
import com.example.EmployeeLeave.repository.DepartmentRepository;
import com.example.EmployeeLeave.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        // Prevent creating duplicate departments using the method from your repository
        if (departmentRepository.existsByDepartmentName(departmentDTO.getDepartmentName())) {
            throw new RuntimeException("Department already exists with name: " + departmentDTO.getDepartmentName());
        }

        Department department = departmentMapper.toEntity(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);

        return departmentMapper.toDTO(savedDepartment);
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));

        return departmentMapper.toDTO(department);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();

        return departments.stream()
                .map(departmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO updateDepartment(Long id, DepartmentDTO departmentDTO) {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));

        // If they are changing the name, ensure the new name doesn't already exist
        if (!existingDepartment.getDepartmentName().equals(departmentDTO.getDepartmentName()) &&
                departmentRepository.existsByDepartmentName(departmentDTO.getDepartmentName())) {
            throw new RuntimeException("Department already exists with name: " + departmentDTO.getDepartmentName());
        }

        // Update fields
        existingDepartment.setDepartmentName(departmentDTO.getDepartmentName());
        existingDepartment.setDescription(departmentDTO.getDescription());

        Department updatedDepartment = departmentRepository.save(existingDepartment);
        return departmentMapper.toDTO(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new RuntimeException("Department not found with id: " + id);
        }
        departmentRepository.deleteById(id);
    }
}