package com.group6.ads.controllers.admin.authentication.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResetPasswordRequest {
    @NotNull
    private String email;
    @NotNull
    private String password;
}