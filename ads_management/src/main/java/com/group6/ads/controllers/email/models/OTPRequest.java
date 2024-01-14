package com.group6.ads.controllers.email.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OTPRequest {
    @NotBlank(message = "Email must be not blank")
    @Email(message = "Invalid email format")
    private String email;
}
