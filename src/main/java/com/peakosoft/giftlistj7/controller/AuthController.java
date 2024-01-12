package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.AuthRequest;
import com.peakosoft.giftlistj7.model.dto.AuthResponse;
import com.peakosoft.giftlistj7.model.dto.LoginRequest;
import com.peakosoft.giftlistj7.model.dto.LoginResponse;
import com.peakosoft.giftlistj7.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@PreAuthorize("permitAll()")
@RequestMapping("api/auth")
@Tag(name="Auth controller",description = "Used by everyone")
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-up")
    @Operation(summary = "Post auth_controller",description = "Getting auth from user")
    public AuthResponse registration(@RequestBody AuthRequest authRequest) {
        return userService.registration(authRequest);
    }
    @PostMapping("/sign-in")
    @Operation(summary = "Post auth controller",description = "")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
    @GetMapping("/sign-in-with-google")
    @Operation(summary = "Get auth controller",description = "")
    public Map<String, Object> addUser(OAuth2AuthenticationToken oAuth2AuthenticationToken) throws IllegalAccessException{
        return userService.saveWithGoogle(oAuth2AuthenticationToken);
    }

}
