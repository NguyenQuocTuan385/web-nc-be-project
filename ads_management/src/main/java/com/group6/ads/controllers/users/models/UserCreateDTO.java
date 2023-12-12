package com.group6.ads.controllers.users.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.group6.ads.repositories.database.properties.Property;
import com.group6.ads.repositories.database.roles.Roles;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserCreateDTO {
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
