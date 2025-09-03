package com.example.todobe.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserResponseDto {

    private Integer userId;
    private String username;
    private String email;
    private Set<Integer> roleIds;
}
