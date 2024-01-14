package com.group6.ads.controllers.admin.authentication.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResetPasswordRequest {
    @NotBlank(message = "Email must be not blank")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "password must not be blank")
    private String password;
}
