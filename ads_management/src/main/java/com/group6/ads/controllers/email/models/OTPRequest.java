package com.group6.ads.controllers.email.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OTPRequest {
    @NotNull
    private String email;
}
