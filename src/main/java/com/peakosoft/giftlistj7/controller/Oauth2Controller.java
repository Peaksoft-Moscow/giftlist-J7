package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequestMapping("/api/oauth2")
@RequiredArgsConstructor
public class Oauth2Controller {
    private final UserService userService;

    @GetMapping("/with-google")
    public Map<String, Object> addUser(OAuth2AuthenticationToken oAuth2AuthenticationToken) throws IllegalAccessException {
        return userService.saveWithGoogle(oAuth2AuthenticationToken);
    }
}