package com.example.EmployeeLeave.serviceimpl;
import com.example.EmployeeLeave.dto.LeaveRequestDTO;
import com.example.EmployeeLeave.entity.Employee;
import com.example.EmployeeLeave.entity.LeaveRequest;
import com.example.EmployeeLeave.mapper.LeaveRequestMapper;
import com.example.EmployeeLeave.repository.EmployeeRepository;
import com.example.EmployeeLeave.repository.LeaveRequestRepository;
import com.example.EmployeeLeave.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveRequestMapper leaveRequestMapper;

    @Autowired
    public LeaveRequestServiceImpl(LeaveRequestRepository leaveRequestRepository,
                                   EmployeeRepository employeeRepository,
                                   LeaveRequestMapper leaveRequestMapper) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
        this.leaveRequestMapper = leaveRequestMapper;
    }

    @Override
    public LeaveRequestDTO createLeaveRequest(LeaveRequestDTO leaveRequestDTO) {
        LeaveRequest leaveRequest = leaveRequestMapper.toEntity(leaveRequestDTO);

        // Validate and attach the Employee
        if (leaveRequestDTO.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(leaveRequestDTO.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("Employee not found with id: " + leaveRequestDTO.getEmployeeId()));
            leaveRequest.setEmployee(employee);
        } else {
            throw new RuntimeException("Employee ID is required to create a leave request.");
        }

        // Default status if not provided (e.g., PENDING)
        if (leaveRequest.getStatus() == null || leaveRequest.getStatus().isEmpty()) {
            leaveRequest.setStatus("PENDING");
        }

        LeaveRequest savedRequest = leaveRequestRepository.save(leaveRequest);
        return leaveRequestMapper.toDTO(savedRequest);
    }

    @Override
    public LeaveRequestDTO getLeaveRequestById(Long id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave Request not found with id: " + id));
        return leaveRequestMapper.toDTO(leaveRequest);
    }

    @Override
    public List<LeaveRequestDTO> getAllLeaveRequests() {
        return leaveRequestRepository.findAll().stream()
                .map(leaveRequestMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LeaveRequestDTO updateLeaveRequest(Long id, LeaveRequestDTO leaveRequestDTO) {
        LeaveRequest existingRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave Request not found with id: " + id));

        // Update fields
        existingRequest.setLeaveType(leaveRequestDTO.getLeaveType());
        existingRequest.setStartDate(leaveRequestDTO.getStartDate());
        existingRequest.setEndDate(leaveRequestDTO.getEndDate());
        existingRequest.setNumberOfDays(leaveRequestDTO.getNumberOfDays());
        existingRequest.setReason(leaveRequestDTO.getReason());
        existingRequest.setStatus(leaveRequestDTO.getStatus());
        existingRequest.setApprovedDate(leaveRequestDTO.getApprovedDate());
        existingRequest.setRemarks(leaveRequestDTO.getRemarks());

        // Update Employee if changed
        if (leaveRequestDTO.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(leaveRequestDTO.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("Employee not found with id: " + leaveRequestDTO.getEmployeeId()));
            existingRequest.setEmployee(employee);
        }

        LeaveRequest updatedRequest = leaveRequestRepository.save(existingRequest);
        return leaveRequestMapper.toDTO(updatedRequest);
    }

    @Override
    public void deleteLeaveRequest(Long id) {
        if (!leaveRequestRepository.existsById(id)) {
            throw new RuntimeException("Leave Request not found with id: " + id);
        }
        leaveRequestRepository.deleteById(id);
    }
}