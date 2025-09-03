package com.example.todobe.service;

import com.example.todobe.dto.UserDto;
import com.example.todobe.dto.request.CreateAdminRequest;
import com.example.todobe.dto.response.UserResponseDto;

import java.util.List;

public interface IUserService {

    List<UserDto> getAllUsers();

    UserResponseDto createAdmin(CreateAdminRequest createAdminRequest);
}
