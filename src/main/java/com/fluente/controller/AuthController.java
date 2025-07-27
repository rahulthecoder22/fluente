package com.fluente.controller;

import com.fluente.dto.AuthRequest;
import com.fluente.dto.AuthResponse;
import com.fluente.dto.RegisterRequest;
import com.fluente.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// injects AuthenticationService automatically via constructor
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor 
public class AuthController {

    private final AuthenticationService authenticationService;

    // Handle user registration
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        AuthResponse response = authenticationService.register(request);
        return ResponseEntity.ok(response);
    }

    // Handle user login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
    

