package com.peakosoft.giftlistj7.service;

import com.peakosoft.giftlistj7.model.dto.mapper.UserMapper;
import com.peakosoft.giftlistj7.model.dto.AuthRequest;
import com.peakosoft.giftlistj7.model.dto.AuthResponse;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.model.enums.Role;
import com.peakosoft.giftlistj7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse registration(AuthRequest authRequest) {
        User user = userMapper.mapToEntity(authRequest);
        if (user.getName().length() < 2 || user.getLastName().length() < 2) {
            throw new RuntimeException("Имя и фамилия должны содержать более двух символов!");
        }
        if (!user.getEmail().contains("@")){
            throw new RuntimeException("Email пользователя должен содержать @!");
        }
        List<User> users = userRepository.findAll();
        for (User user2 : users) {
            if (user2.getEmail().equals(user.getEmail())) {
                throw new RuntimeException("Пользователь с таким email уже существует!");
            }
        }
        if (user.getPassword().length() < 6) {
            throw new RuntimeException("Пароль пользователя должен содеражать не менее 6 символов!");
        }
        if (!user.getPassword().equals(authRequest.getRepeatPassword())) {
            throw new RuntimeException("Пароль не совпадает с выбранным паролем!");
        }
        boolean containsUppercase = false;
        boolean containsLowercase = false;
        boolean containsDigit = false;
        for (char c : user.getPassword().toCharArray()) {
            if (Character.isUpperCase(c)) {
                containsUppercase = true;
            } else if (Character.isLowerCase(c)) {
                containsLowercase = true;
            } else if (Character.isDigit(c)) {
                containsDigit = true;
            }
        }
        if (containsUppercase != true) {
            throw new RuntimeException("Пароль пользователя должен содержать большие латинские буквы!");
        }
        if (containsLowercase != true) {
            throw new RuntimeException("Пароль пользователя должен содержать маленькие латинские буквы!");
        }
        if (containsDigit != true) {
            throw new RuntimeException("Пароль пользователя должен содержать цифры!");
        }
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        List<User> users1 = userRepository.findAll();
        for (User user1 : users1) {
            if (user1 == null) {
                Role role = Role.ADMIN;
                user.setRole(role);
            } else {
                user.setRole(Role.USER);
            }
        }
        userRepository.save(user);
        return userMapper.mapToResponse(user);
    }

}