package com.group6.ads.controllers.authentication.models;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {
    String name;
    String email;
    String password;
    LocalDate birthday;
    String avatar;
    String phone;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "property_id")
    private Integer propertyId;
}
