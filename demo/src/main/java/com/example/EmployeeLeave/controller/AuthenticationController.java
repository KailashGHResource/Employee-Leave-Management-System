package com.example.EmployeeLeave.controller;

import com.example.EmployeeLeave.dto.AuthenticationResponse;
import com.example.EmployeeLeave.dto.LoginRequest;
import com.example.EmployeeLeave.entity.Employee; // Ensure this matches your package
import com.example.EmployeeLeave.repository.EmployeeRepository; // Ensure this matches your package
import com.example.EmployeeLeave.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder; // Required for hashing
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    // 1. Add new dependencies for registration
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    // 2. Update the constructor to inject all three dependencies
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    EmployeeRepository employeeRepository,
                                    PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // POST /api/auth/login endpoint (Unchanged)
    @PostMapping("/login")
    // POST /api/auth/login endpoint
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) { // Change 1: Return <?> instead of <String>
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Change 2: Generate the token using your JwtUtil
            String jwt = jwtUtil.generateToken(loginRequest.getUsername());

            // Change 3: Return the token inside the response object
            return ResponseEntity.ok(new AuthenticationResponse(jwt));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Invalid email or password");
        }
    }

    // 3. Add the new POST /api/auth/register endpoint
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Employee employee) {

        // Step A: Check if a user with this email already exists
        if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Email is already taken!");
        }

        // Step B: Hash the plain text password from the request body
        String encodedPassword = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(encodedPassword);

        // Step C: Save the new user to the database
        employeeRepository.save(employee);

        return ResponseEntity.ok("User registered successfully");
    }
}