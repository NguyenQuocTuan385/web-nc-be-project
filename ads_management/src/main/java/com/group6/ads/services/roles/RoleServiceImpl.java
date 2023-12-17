package com.group6.ads.services.roles;

import com.group6.ads.controllers.roles.models.RoleRequest;
import com.group6.ads.repositories.database.roles.Role;
import com.group6.ads.repositories.database.roles.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role createRole(RoleRequest role) {
        Role roleCreated = Role.builder()
                .code(role.getCode())
                .description(role.getDescription())
                .build();
        return roleRepository.save(roleCreated);
    }

    @Override
    public Role updateRole(RoleRequest role, Integer theId) {
        Role foundRole = roleRepository.findById(theId).orElseThrow(() -> new RuntimeException("Role not found"));

        foundRole.setCode(role.getCode());
        foundRole.setDescription(role.getDescription());

        return roleRepository.save(foundRole);
    }

    @Override
    public void delete(Integer id) {
        if(roleRepository.existsById(id)) {
           roleRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Role not found");
        }
    }
}
