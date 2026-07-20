package com.example.EmployeeLeave.service;

import com.example.EmployeeLeave.dto.LeaveRequestDTO;
import java.util.List;

public interface LeaveRequestService {

    LeaveRequestDTO createLeaveRequest(LeaveRequestDTO leaveRequestDTO);

    LeaveRequestDTO getLeaveRequestById(Long id);

    List<LeaveRequestDTO> getAllLeaveRequests();

    LeaveRequestDTO updateLeaveRequest(Long id, LeaveRequestDTO leaveRequestDTO);

    void deleteLeaveRequest(Long id);
}