package com.group6.ads.controllers;


import com.group6.ads.controllers.users.models.UserRequest;
import com.group6.ads.repositories.database.users.Users;
import com.group6.ads.services.users.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/users")
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<Users> createUsers(@RequestBody @Valid UserRequest users) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUsers(users));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUsers(@RequestBody @Valid UserRequest users, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUsers(users, id));
    }


}
