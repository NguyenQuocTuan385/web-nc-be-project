package com.group6.ads.controllers.authentication;

import com.group6.ads.controllers.authentication.models.LoginRequest;
import com.group6.ads.controllers.authentication.models.RefreshRequest;
import com.group6.ads.controllers.authentication.models.RegisterRequest;
import com.group6.ads.services.authentication.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/")
@CrossOrigin("*")
public class AuthenticationController {
    @NonNull
    final AuthenticationService authenticationService;

    @PostMapping("authentication/login")
    public ResponseEntity<HashMap<String, String>> login(@Valid @RequestBody LoginRequest userLoginRequest,
                                                         HttpServletResponse response) {
        try {
            HashMap<String, String> res = authenticationService.login(userLoginRequest.getEmail(), userLoginRequest.getPassword());

            // Create and set cookie with secure attributes
            Cookie cookie = new Cookie("refreshToken", res.get("refresh_token"));
            cookie.setPath("/"); // Adjust path as needed
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            response.addCookie(cookie);

            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            HashMap<String, String> map = new HashMap<>();
            map.put("error" , e.getMessage());

            return ResponseEntity.badRequest().body(map);
        }
    }

    @PostMapping("authentication/register")
    ResponseEntity<?> register(@RequestBody @Valid RegisterRequest registerRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.register(registerRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("authentication/refresh")
    public ResponseEntity<?> refresh(@CookieValue(value = "refreshToken", defaultValue = "") String refreshToken) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(authenticationService.refresh(refreshToken));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("authentication/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        try {
            authenticationService.logout(response);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
