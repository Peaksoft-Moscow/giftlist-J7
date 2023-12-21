package com.giftlistj7.peakosoft.mapper;

import com.giftlistj7.peakosoft.model.dto.LoginResponse;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {
    public LoginResponse mapToResponse(String token, String roleName) {
        return LoginResponse.builder()
                .token(token)
                .roleName(roleName)
                .build();
    }
}
