package com.example.todobe.service;

import com.example.todobe.dto.UserDto;
import com.example.todobe.dto.response.AuthResponse;

import java.util.List;

public interface IUserService {

    List<UserDto> getAllUsers();

    AuthResponse
}
