package com.group6.ads.controllers;

import com.group6.ads.dtos.PropertyCreateDTO;
import com.group6.ads.dtos.RoleCreateDTO;
import com.group6.ads.entities.Properties;
import com.group6.ads.entities.Roles;
import com.group6.ads.services.PropertyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/properties")
public class PropertyController {
    private final PropertyService propertyService;

    @GetMapping("")
    ResponseEntity<List<Properties>> getAllRoles() {
        return ResponseEntity.status(HttpStatus.OK).body(propertyService.findAll());
    }

    @PostMapping("")
    ResponseEntity<Properties> createRoles(@RequestBody @Valid PropertyCreateDTO properties) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.createProperties(properties));
    }

}
