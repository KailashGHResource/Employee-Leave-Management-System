package com.example.EmployeeLeave.controller;

import com.example.EmployeeLeave.dto.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    // Inject the AuthenticationManager via constructor
    public AuthenticationController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // POST /api/auth/login endpoint as required by Story 2 and Story 6
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            // This triggers the CustomUserDetailsService to load the user by email
            // and verifies the raw password against the encrypted password in the database
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            // Set the authenticated user in the global security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Temporary response. This will be replaced with the JWT token logic in Day 3.
            return ResponseEntity.ok("Login Successful");

        } catch (BadCredentialsException e) {
            // Return 401 Unauthorized if the email or password doesn't match
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Invalid email or password");
        }
    }
}