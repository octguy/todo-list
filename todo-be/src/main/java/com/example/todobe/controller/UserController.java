package com.example.todobe.controller;

import com.example.todobe.dto.UserDto;
import com.example.todobe.dto.request.CreateAdminRequest;
import com.example.todobe.dto.response.UserResponseDto;
import com.example.todobe.model.ApiResponse;
import com.example.todobe.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create-admin")
    public ResponseEntity<ApiResponse<UserResponseDto>> createAdmin(@RequestBody @Valid CreateAdminRequest createAdminRequest) {
        // Implementation for creating an admin user would go here
        UserResponseDto userResponseDto = userService.createAdmin(createAdminRequest);

        ApiResponse<UserResponseDto> apiResponse = new ApiResponse<>(
                HttpStatus.CREATED,
                "Create admin successfully",
                userResponseDto,
                null
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
