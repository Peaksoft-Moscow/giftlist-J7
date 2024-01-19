package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/oauth2")
@PreAuthorize("permitAll()")
@RequiredArgsConstructor
@Tag(name = "OAuth2 Controller", description = "Operations related to OAuth2 authentication")
public class Oauth2Controller {
    private final UserService userService;

    @GetMapping("/with-google")
    @Operation(summary = "Authenticate user with Google",
            description = "Authenticates a user using OAuth2 with Google")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully authenticated user"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Map<String, Object> addUser(OAuth2AuthenticationToken oAuth2AuthenticationToken) throws IllegalAccessException {
        return userService.saveWithGoogle(oAuth2AuthenticationToken);
    }
}
