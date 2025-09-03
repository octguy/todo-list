package com.example.todobe.service.implementation;

import com.example.todobe.dto.UserDto;
import com.example.todobe.dto.request.CreateAdminRequest;
import com.example.todobe.dto.response.AuthResponse;
import com.example.todobe.dto.response.UserResponseDto;
import com.example.todobe.exception.BadRequestException;
import com.example.todobe.model.Role;
import com.example.todobe.model.User;
import com.example.todobe.repository.RoleRepository;
import com.example.todobe.repository.UserRepository;
import com.example.todobe.service.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public UserResponseDto createAdmin(CreateAdminRequest createAdminRequest) {
        if (userRepository.existsByEmail(createAdminRequest.getEmail())) {
            throw new BadRequestException("Email is already in use");
        }

        if (userRepository.existsByUsername(createAdminRequest.getUsername())) {
            throw new BadRequestException("Email is already in use");
        }

        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("User Admin not set"));

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("User Role not set"));

        User user = new User();
        user.setEmail(createAdminRequest.getEmail());
        user.setUsername(createAdminRequest.getUsername());
        user.setPassword(passwordEncoder.encode(createAdminRequest.getPassword()));
        user.setRoles(new HashSet<>());
        user.getRoles().add(adminRole);
        user.getRoles().add(userRole);

        userRepository.save(user);

        return UserResponseDto.builder().
                userId(user.getUserId()).
                username(user.getUsername()).
                email(user.getEmail()).
                roleIds(user.getRoles().stream().map(Role::getRoleId).collect(Collectors.toSet())).
                build();
    }

    private UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
