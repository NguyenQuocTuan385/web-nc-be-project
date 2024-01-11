package com.group6.ads.controllers.users.models;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserGuestRequest {
    @NotBlank(message = "Email must be not blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone must be not blank")
    @Size(min = 1, max = 20, message = "Phone must be between 1 and 20")
    private String phone;

    private String password;
}
