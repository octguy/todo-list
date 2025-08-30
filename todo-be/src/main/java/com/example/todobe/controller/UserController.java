package com.example.todobe.controller;

import com.example.todobe.dto.UserDto;
import com.example.todobe.model.ApiResponse;
import com.example.todobe.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        ApiResponse<List<UserDto>> apiResponse = new ApiResponse<>(
                HttpStatus.OK,
                "Users retrieved successfully",
                users,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }
}
