package com.group6.ads.controllers.admin.roles;

import com.group6.ads.controllers.admin.roles.models.RoleRequest;
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
    ResponseEntity<?> getAllRoles() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(roleService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("")
    ResponseEntity<?> createRole(@RequestBody @Valid RoleRequest role) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(role));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateRole(@RequestBody @Valid RoleRequest role, @PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(roleService.updateRole(role, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteRole(@PathVariable Integer id) {
        try {
            roleService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
