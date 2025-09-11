package com.example.todobe.service.implementation;

import com.example.todobe.dto.request.LoginRequest;
import com.example.todobe.dto.request.RegisterRequest;
import com.example.todobe.dto.response.AuthResponse;
import com.example.todobe.exception.BadRequestException;
import com.example.todobe.exception.ResourceNotFoundException;
import com.example.todobe.jwt.JwtUtil;
import com.example.todobe.model.CustomUserDetails;
import com.example.todobe.model.Role;
import com.example.todobe.model.User;
import com.example.todobe.repository.RoleRepository;
import com.example.todobe.repository.UserRepository;
import com.example.todobe.service.IAuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
                           UserRepository userRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        // Authenticate user (automatically throw BadCredentialsException if authentication fails)
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        // Get user details
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Bad credentials"));

        Set<Integer> roleIds = user.getRoles().stream()
                .map(Role::getRoleId)
                .collect(Collectors.toSet());

        // Generate JWT token
        String token = jwtUtil.generateToken(userDetails);

        return AuthResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .accessToken(token)
                .roleIds(roleIds)
                .build();
    }

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new BadRequestException("Email is already in use");
        }

        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new BadRequestException("Email is already in use");
        }

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("User Role not set"));

        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRoles(new HashSet<>(Collections.singleton(userRole)));

        userRepository.save(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(registerRequest.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        return AuthResponse.builder()
                .userId(userRepository.save(user).getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .accessToken(token)
                .roleIds(user.getRoles().stream().map(Role::getRoleId).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        return customUserDetails.getUser();
    }
}
