package com.example.EmployeeLeave.security;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    // POST /api/auth/login endpoint as required by Story 6
    @PostMapping("/login")
    public ResponseEntity<String> login() {
        // Logic for Employee Login and HR Login goes here
        return ResponseEntity.ok("Login Successful");
    }
}