package com.group6.ads.controllers.admin.users.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserOTPRequest {
    @NotNull
    private String email;
    @NotNull
    private String otp;
}
