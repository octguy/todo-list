package com.example.todobe.service;

import com.example.todobe.dto.UserDto;

import java.util.List;

public interface IUserService {

    List<UserDto> getAllUsers();
}
