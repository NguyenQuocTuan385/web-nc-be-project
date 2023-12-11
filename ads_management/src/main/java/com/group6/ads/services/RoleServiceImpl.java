package com.group6.ads.services;

import com.group6.ads.dtos.RoleCreateDTO;
import com.group6.ads.entities.Roles;
import com.group6.ads.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    @Override
    public List<Roles> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Roles createRoles(RoleCreateDTO roles) {
        Roles rolesCreated = Roles.builder()
                .code(roles.getCode())
                .description(roles.getDescription())
                .build();
        return roleRepository.save(rolesCreated);
    }
}
