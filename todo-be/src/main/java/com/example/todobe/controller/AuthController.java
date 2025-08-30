package com.example.todobe.controller;

import com.example.todobe.dto.request.LoginRequest;
import com.example.todobe.dto.request.RegisterRequest;
import com.example.todobe.dto.response.AuthResponse;
import com.example.todobe.model.ApiResponse;
import com.example.todobe.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody @Valid LoginRequest loginRequest) {
        AuthResponse authResponse = authService.login(loginRequest);
        ApiResponse<AuthResponse> apiResponse = new ApiResponse<>(
                HttpStatus.OK,
                "Login successful",
                authResponse,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@RequestBody @Valid RegisterRequest registerRequest) {
        AuthResponse authResponse = authService.register(registerRequest);
        ApiResponse<AuthResponse> apiResponse = new ApiResponse<>(
                HttpStatus.CREATED,
                "User registered successfully",
                authResponse,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }
}
