package com.example.EmployeeLeave.repository;

import com.example.EmployeeLeave.entity.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    // Standard CRUD methods are inherited from JpaRepository
}