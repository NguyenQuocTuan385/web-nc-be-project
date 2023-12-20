package com.group6.ads.services.roles;

import com.group6.ads.controllers.roles.models.RoleRequest;
import com.group6.ads.repositories.database.roles.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role createRole(RoleRequest role);

    Role updateRole(RoleRequest role, Integer theId);

    void delete(Integer id);
}