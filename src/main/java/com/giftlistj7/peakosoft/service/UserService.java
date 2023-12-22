package com.giftlistj7.peakosoft.service;

import com.giftlistj7.peakosoft.mapper.LoginMapper;
import com.giftlistj7.peakosoft.model.dto.LoginRequest;
import com.giftlistj7.peakosoft.model.dto.LoginResponse;
import com.giftlistj7.peakosoft.model.entities.User;
import com.giftlistj7.peakosoft.config.UserRepository;
import com.giftlistj7.peakosoft.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final LoginMapper loginMapper;

    public LoginResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid email or password");
        }
        User user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Not found"));
        String jwt = jwtUtil.generateToken(user);
        return loginMapper.mapToResponse(jwt, user.getRoles().toString());
    }

}
