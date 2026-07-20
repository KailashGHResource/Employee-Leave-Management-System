package com.example.EmployeeLeave.serviceimpl;
import com.example.EmployeeLeave.dto.AddressDTO;
import com.example.EmployeeLeave.entity.Address;
import com.example.EmployeeLeave.entity.Employee;
import com.example.EmployeeLeave.mapper.AddressMapper;
import com.example.EmployeeLeave.repository.AddressRepository;
import com.example.EmployeeLeave.repository.EmployeeRepository;
import com.example.EmployeeLeave.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final EmployeeRepository employeeRepository;
    private final AddressMapper addressMapper;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository,
                              EmployeeRepository employeeRepository,
                              AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO) {
        Address address = addressMapper.toEntity(addressDTO);

        // Validate and attach the Employee if an ID is provided
        if (addressDTO.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(addressDTO.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("Employee not found with id: " + addressDTO.getEmployeeId()));
            address.setEmployee(employee);
        }

        Address savedAddress = addressRepository.save(address);
        return addressMapper.toDTO(savedAddress);
    }

    @Override
    public AddressDTO getAddressById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
        return addressMapper.toDTO(address);
    }

    @Override
    public List<AddressDTO> getAllAddresses() {
        return addressRepository.findAll().stream()
                .map(addressMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDTO updateAddress(Long id, AddressDTO addressDTO) {
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));

        // Update fields
        existingAddress.setHouseNo(addressDTO.getHouseNo());
        existingAddress.setStreet(addressDTO.getStreet());
        existingAddress.setCity(addressDTO.getCity());
        existingAddress.setState(addressDTO.getState());
        existingAddress.setPincode(addressDTO.getPincode());

        // Update Employee link if provided
        if (addressDTO.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(addressDTO.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("Employee not found with id: " + addressDTO.getEmployeeId()));
            existingAddress.setEmployee(employee);
        } else {
            existingAddress.setEmployee(null);
        }

        Address updatedAddress = addressRepository.save(existingAddress);
        return addressMapper.toDTO(updatedAddress);
    }

    @Override
    public void deleteAddress(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new RuntimeException("Address not found with id: " + id);
        }
        addressRepository.deleteById(id);
    }
}