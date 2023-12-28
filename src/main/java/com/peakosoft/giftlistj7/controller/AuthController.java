package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.AuthRequest;
import com.peakosoft.giftlistj7.model.dto.AuthResponse;
import com.peakosoft.giftlistj7.model.dto.LoginRequest;
import com.peakosoft.giftlistj7.model.dto.LoginResponse;
import com.peakosoft.giftlistj7.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public AuthResponse registration(@RequestBody AuthRequest authRequest) {
        return userService.registration(authRequest);
    }
    @PostMapping("/sign-in")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }


}
