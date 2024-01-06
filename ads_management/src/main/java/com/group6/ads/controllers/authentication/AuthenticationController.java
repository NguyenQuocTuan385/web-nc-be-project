package com.group6.ads.controllers.authentication;

import com.group6.ads.controllers.authentication.models.LoginRequest;
import com.group6.ads.controllers.authentication.models.RefreshRequest;
import com.group6.ads.controllers.authentication.models.RegisterRequest;
import com.group6.ads.services.authentication.AuthenticationService;
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
    public ResponseEntity<HashMap<String, String>> login(@Valid @RequestBody LoginRequest userLoginRequest) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(authenticationService.login(userLoginRequest.getEmail(), userLoginRequest.getPassword()));
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
    public ResponseEntity<?> refresh(@Valid @RequestBody RefreshRequest refreshRequest) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(authenticationService.refresh(refreshRequest.getRefreshToken()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
