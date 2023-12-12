package com.group6.ads.controllers.roles;

import com.group6.ads.controllers.roles.models.RoleCreateDTO;
import com.group6.ads.repositories.database.roles.Roles;
import com.group6.ads.services.roles.RoleService;
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
