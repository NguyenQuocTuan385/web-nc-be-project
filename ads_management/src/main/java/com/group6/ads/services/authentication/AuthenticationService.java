package com.group6.ads.services.authentication;

import com.group6.ads.controllers.authentication.models.ChangePasswordRequest;
import com.group6.ads.controllers.authentication.models.RegisterRequest;
import com.group6.ads.controllers.authentication.models.ResetPasswordRequest;
import com.group6.ads.repositories.database.users.User;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;

public interface AuthenticationService {
    User register(RegisterRequest registerRequest) throws Exception;
    User changePassword(ChangePasswordRequest changePasswordRequest) throws Exception;

    User resetPassword(ResetPasswordRequest resetPasswordRequest) throws Exception;

    HashMap<String, String> login(String email, String password) throws Exception;

    HashMap<String, String> refresh(String token) throws Exception;

    void logout(HttpServletResponse response) throws  Exception;
}
