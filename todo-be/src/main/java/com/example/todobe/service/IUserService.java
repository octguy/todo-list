package com.example.todobe.service;

import com.example.todobe.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUserService {

    List<UserDto> getAllUsers();
}
