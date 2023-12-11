package com.group6.ads.services.roles;

import com.group6.ads.controllers.roles.models.RoleCreateDTO;
import com.group6.ads.repositories.database.roles.Roles;

import java.util.List;

public interface RoleService {
    List<Roles> findAll();
    Roles createRoles(RoleCreateDTO roles);
}
