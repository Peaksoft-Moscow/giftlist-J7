package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name = "Oauth2 controller",description = "Used by everyone")
public class Oauth2Controller {
    private final UserService userService;

    @GetMapping("/with-google")
    @Operation(summary = "Get oauth2 controller",description = "Getting auth from user")
    public Map<String, Object> addUser(OAuth2AuthenticationToken oAuth2AuthenticationToken) throws IllegalAccessException {
        return userService.saveWithGoogle(oAuth2AuthenticationToken);
    }
}
