package com.peakosoft.giftlistj7.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    @NotNull
    @Size(min = 2, max = 20, message = "Имя должно содержать от 2 до 20 букв!")
    private String name;
    @Min(2)
    @Size(min = 2, max = 20, message = "Фамилия должна содержать от 2 до 20 букв!")
    private String lastName;
    @Email(message = "Email пользователя должен содержать @!")
    @Size(min = 5, max = 30, message = "Email пользователя должен содержать от 5 до 30 символов!")
    private String email;
    @NotNull
    @Min(value = 6, message = "Пароль пользователя должен содержать минимум 6 символов")
    private String password;
    @NotNull
    private String repeatPassword;
    private boolean subscribe;
}