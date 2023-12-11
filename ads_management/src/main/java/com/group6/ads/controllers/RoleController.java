package com.group6.ads.controllers;

import com.group6.ads.dtos.RoleCreateDTO;
import com.group6.ads.entities.Roles;
import com.group6.ads.services.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/roles")
public class RoleController{
    private final RoleService roleService;


    @GetMapping("")
    ResponseEntity<List<Roles>> getAllRoles() {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.findAll());
    }

    @PostMapping("")
    ResponseEntity<Roles> createRoles(@RequestBody @Valid RoleCreateDTO roles) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRoles(roles));
    }

}
