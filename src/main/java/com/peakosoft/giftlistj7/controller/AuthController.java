package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.AuthRequest;
import com.peakosoft.giftlistj7.model.dto.AuthResponse;
import com.peakosoft.giftlistj7.model.dto.LoginRequest;
import com.peakosoft.giftlistj7.model.dto.LoginResponse;
import com.peakosoft.giftlistj7.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    @GetMapping("/sign-in-with-google")
    public Map<String, Object> addUser(OAuth2AuthenticationToken oAuth2AuthenticationToken) throws IllegalAccessException{
        return userService.saveWithGoogle(oAuth2AuthenticationToken);
    }

}
