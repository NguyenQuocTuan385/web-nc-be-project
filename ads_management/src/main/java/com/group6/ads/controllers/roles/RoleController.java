package com.group6.ads.controllers.roles;

import com.group6.ads.controllers.roles.models.RoleRequest;
import com.group6.ads.repositories.database.roles.Role;
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
@CrossOrigin("*")
public class RoleController {
    private final RoleService roleService;

    @GetMapping("")
    ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.findAll());
    }

    @PostMapping("")
    ResponseEntity<Role> createRole(@RequestBody @Valid RoleRequest role) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(role));
    }

    @PutMapping("/{id}")
    ResponseEntity<Role> updateRole(@RequestBody @Valid RoleRequest role, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.updateRole(role, id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
        roleService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
