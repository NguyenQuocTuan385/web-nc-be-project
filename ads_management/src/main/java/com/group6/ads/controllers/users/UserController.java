package com.group6.ads.controllers.users;


import com.group6.ads.controllers.users.models.UserRequest;
import com.group6.ads.repositories.database.users.User;
import com.group6.ads.services.users.UserService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/users")
public class UserController {
    @NonNull
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<User> createUsers(@RequestBody @Valid UserRequest users) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUsers(users));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUsers(@RequestBody @Valid UserRequest users, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUsers(users, id));
    }

    @GetMapping("/test")
    public ResponseEntity<List<User>> test() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll1());
    }
}
