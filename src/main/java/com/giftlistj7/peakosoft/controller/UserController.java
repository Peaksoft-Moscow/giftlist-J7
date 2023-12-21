package com.giftlistj7.peakosoft.controller;

import com.giftlistj7.peakosoft.model.dto.LoginRequest;
import com.giftlistj7.peakosoft.model.dto.LoginResponse;
import com.giftlistj7.peakosoft.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-in")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

}
