package com.group6.ads.services.roles;

import com.group6.ads.controllers.roles.models.RoleCreateDTO;
import com.group6.ads.repositories.database.roles.Roles;
import com.group6.ads.repositories.database.roles.RoleRepository;
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
