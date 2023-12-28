package com.peakosoft.giftlistj7.model.dto.mapper;

import com.peakosoft.giftlistj7.model.dto.LoginResponse;
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
