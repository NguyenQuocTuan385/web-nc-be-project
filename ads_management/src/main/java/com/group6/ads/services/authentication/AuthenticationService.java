package com.group6.ads.services.authentication;

import com.group6.ads.controllers.authentication.models.RegisterRequest;
import com.group6.ads.repositories.database.users.User;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;

public interface AuthenticationService {
    User register(RegisterRequest registerRequest) throws Exception;

    HashMap<String, String> login(String email, String password) throws Exception;

    HashMap<String, String> refresh(String token) throws Exception;

    void logout(HttpServletResponse response) throws  Exception;
}
