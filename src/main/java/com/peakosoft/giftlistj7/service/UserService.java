package com.peakosoft.giftlistj7.service;

import com.peakosoft.giftlistj7.config.jwt.JwtUtil;
import com.peakosoft.giftlistj7.model.dto.*;
import com.peakosoft.giftlistj7.model.dto.mapper.LoginMapper;
import com.peakosoft.giftlistj7.model.dto.mapper.UserMapper;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.model.enums.Role;
import com.peakosoft.giftlistj7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final LoginMapper loginMapper;
    private final MailSender mailSender;


    public AuthResponse registration(AuthRequest authRequest) {
        User user = userMapper.mapToEntity(authRequest);
        if (user.getName().length() < 2 || user.getLastName().length() < 2) {
            throw new RuntimeException("Имя и фамилия должны содержать более двух символов!");
        }
        if (!user.getEmail().contains("@")) {
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


    public LoginResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid email or password");
        }
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("not found"));
        String jwt = jwtUtil.generateToke(user);
        return loginMapper.mapToResponse(jwt, user.getRole().toString());
    }

    public Map<String,Object> saveWithGoogle(OAuth2AuthenticationToken oAuth2AuthenticationToken) throws IllegalAccessException{
        OAuth2AuthenticatedPrincipal principal = oAuth2AuthenticationToken.getPrincipal();
        if(oAuth2AuthenticationToken == null) {
            throw new IllegalAccessException("The token must be not null");
        }
        Map<String,Object> json = principal.getAttributes();
        User user =new User();
        user.setName((String) json.get("given_name"));
        user.setLastName((String) json.get("family_name"));
        user.setEmail((String) json.get("email"));
        user.setPassword((String) json.get("given_name"));
        user.setLocalDate(LocalDate.now());
        userRepository.save(user);
        Map<String,Object> response = new LinkedHashMap<>();
        response.put("name",user.getName());
        response.put("last_name",user.getLastName());
        response.put("email",user.getEmail());
        response.put("createDate",user.getLocalDate());
        return response;
    }


    public String sendCode(String email){
       User user = userRepository.findByEmail(email).orElse(null);
        String code = UUID.randomUUID().toString();
        if (user != null) {
            user.setActivationCode(code);
            mailSender.send(email, "forgot-password", code);
            userRepository.save(user);
            return "Code sent";
        } else {
            return "Code is not sent";
        }

    }

    public boolean changePassword(String code, String email, String password, String confirmPassword) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("not found"));
        System.out.println(code == user.getActivationCode());

        if (!user.getActivationCode().equals(code)) {
            return false;
        }
        if (!password.equals(confirmPassword)) {
            throw new RuntimeException("Passwords do not match");
        }
        if (password.length() < 6 || !password.matches(".*[A-Z].*")) {
            throw new RuntimeException("Пароль должен иметь длину не менее 6 символов и содержать хотя бы одну заглавную букву");
        }
        user.setActivationCode(null);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return true;
    }
}
