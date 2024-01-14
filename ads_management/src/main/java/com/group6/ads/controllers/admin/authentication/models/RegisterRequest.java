package com.group6.ads.controllers.admin.authentication.models;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {
    @NotBlank(message = "Name must be not blank")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 50")
    String name;
    @NotBlank(message = "Email must be not blank")
    @Email(message = "Invalid email format")
    String email;
    @NotBlank(message = "password must be not blank")
    String password;
    @NotNull(message = "Birthday must be not null")
    LocalDate birthday;

    String avatar;
    String phone;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "property_id")
    private Integer propertyId;
}
