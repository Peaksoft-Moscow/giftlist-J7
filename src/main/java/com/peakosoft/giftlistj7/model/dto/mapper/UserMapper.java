package com.peakosoft.giftlistj7.model.dto.mapper;

import com.peakosoft.giftlistj7.config.jwt.JwtUtil;
import com.peakosoft.giftlistj7.model.dto.AuthRequest;
import com.peakosoft.giftlistj7.model.dto.AuthResponse;

import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.model.enums.Role;
import com.peakosoft.giftlistj7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class    UserMapper {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    public User mapToEntity(AuthRequest authRequest) {
        System.out.println(authRequest.getName());
        User user = new User();
        user.setName(authRequest.getName());
        user.setLastName(authRequest.getLastName());
        user.setEmail(authRequest.getEmail());
        user.setPassword(authRequest.getPassword());
        user.setSubscribe(authRequest.isSubscribe());
        user.setRole(Role.ADMIN);
        return user;
    }

    public AuthResponse mapToResponse(User user) {
        return AuthResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .subscribe(user.isSubscribe())
                .response("Пользователь успешно зарегистрирован.")
                .build();
    }

}