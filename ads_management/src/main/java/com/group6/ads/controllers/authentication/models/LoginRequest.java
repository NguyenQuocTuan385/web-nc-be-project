package com.group6.ads.controllers.authentication.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequest {
    String email;
    String password;
}
