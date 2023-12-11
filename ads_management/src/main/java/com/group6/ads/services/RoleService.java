package com.group6.ads.services;

import com.group6.ads.dtos.RoleCreateDTO;
import com.group6.ads.entities.Roles;

import java.util.List;

public interface RoleService {
    List<Roles> findAll();
    Roles createRoles(RoleCreateDTO roles);
}
