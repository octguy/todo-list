package com.example.todobe.service;

import com.example.todobe.dto.request.LoginRequest;
import com.example.todobe.dto.request.RegisterRequest;
import com.example.todobe.dto.response.AuthResponse;
import com.example.todobe.model.User;

public interface IAuthService {

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse register(RegisterRequest registerRequest);

    User getCurrentUser();
}
