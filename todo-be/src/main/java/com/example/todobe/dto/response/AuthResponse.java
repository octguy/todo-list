package com.example.todobe.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AuthResponse {

    private Integer userId;
    private String username;
    private String email;
    private String accessToken;
    private Set<Integer> roleIds;
}
