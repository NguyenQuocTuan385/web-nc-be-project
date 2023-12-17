package com.group6.ads.controllers.users.models;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequest {
    @NotBlank(message = "Name must be not blank")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 50")
    private String name;
    @NotBlank(message = "Email must be not blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password must be not blank")
    private String password;

    @NotNull(message = "Birthday must be not null")
    private LocalDate birthday;

    private String avatar;

    private String phone;

    @NotNull(message = "Role Id must not be null")
    @Column(name = "role_id")
    private Integer roleId;

    @NotNull(message = "Property Id must not be null")
    @Column(name = "property_id")
    private Integer propertyId;

}
