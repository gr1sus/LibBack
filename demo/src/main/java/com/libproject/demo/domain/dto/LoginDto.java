package com.libproject.demo.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {
    @NotBlank(message = "login should not be blank")
    @Email(message = "invalid email format")
    private String login;
    @NotBlank(message = "password should not be blank")
    private String password;
}
