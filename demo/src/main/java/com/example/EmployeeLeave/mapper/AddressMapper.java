package com.example.EmployeeLeave.mapper;
import com.example.EmployeeLeave.dto.AddressDTO;
import com.example.EmployeeLeave.entity.Address;
import com.example.EmployeeLeave.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressDTO toDTO(Address entity) {
        if (entity == null) {
            return null;
        }

        AddressDTO dto = new AddressDTO();
        dto.setId(entity.getId());
        dto.setHouseNo(entity.getHouseNo());
        dto.setStreet(entity.getStreet());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        dto.setPincode(entity.getPincode());

        // Safely extract the Employee ID if the bidirectional relationship is populated
        if (entity.getEmployee() != null) {
            dto.setEmployeeId(entity.getEmployee().getId());
        }

        return dto;
    }

    public Address toEntity(AddressDTO dto) {
        if (dto == null) {
            return null;
        }

        Address entity = new Address();
        entity.setId(dto.getId());
        entity.setHouseNo(dto.getHouseNo());
        entity.setStreet(dto.getStreet());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setPincode(dto.getPincode());

        // Map the employeeId back to an Employee entity reference
        if (dto.getEmployeeId() != null) {
            Employee employee = new Employee();
            employee.setId(dto.getEmployeeId());
            entity.setEmployee(employee);
        }

        return entity;
    }
}