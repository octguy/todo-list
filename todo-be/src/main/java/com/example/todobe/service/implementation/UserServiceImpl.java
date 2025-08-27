package com.example.todobe.service.implementation;

import com.example.todobe.dto.UserDto;
import com.example.todobe.model.User;
import com.example.todobe.repository.UserRepository;
import com.example.todobe.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream().map(this::mapToDto).collect(Collectors.toList());
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
