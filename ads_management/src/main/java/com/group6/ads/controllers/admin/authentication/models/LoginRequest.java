package com.group6.ads.controllers.admin.authentication.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequest {
    @NotBlank(message = "Email must be not blank")
    @Email(message = "Invalid email format")
    String email;
    @NotBlank(message = "password must be not blank")
    String password;
}
