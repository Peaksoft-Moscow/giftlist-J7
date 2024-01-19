package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.AuthRequest;
import com.peakosoft.giftlistj7.model.dto.AuthResponse;
import com.peakosoft.giftlistj7.model.dto.LoginRequest;
import com.peakosoft.giftlistj7.model.dto.LoginResponse;
import com.peakosoft.giftlistj7.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@Tag(name = "Authentication Controller", description = "Operations related to user authentication")
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-up")
    @Operation(summary = "User registration",
            description = "Registers a new user based on the provided registration details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully registered user"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public AuthResponse registration(@RequestBody AuthRequest authRequest) {
        return userService.registration(authRequest);
    }

    @PostMapping("/sign-in")
    @Operation(summary = "User login",
            description = "Logs in a user based on the provided login credentials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully logged in user"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @GetMapping("/sign-in-with-google")
    @Operation(summary = "Authenticate user with Google",
            description = "Authenticates a user using OAuth2 with Google")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully authenticated user"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Map<String, Object> addUser(@Parameter(hidden = true) OAuth2AuthenticationToken oAuth2AuthenticationToken) throws IllegalAccessException {
        return userService.saveWithGoogle(oAuth2AuthenticationToken);
    }

}
