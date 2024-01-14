package com.group6.ads.services.roles;

import com.group6.ads.controllers.admin.roles.models.RoleRequest;
import com.group6.ads.exceptions.NotFoundException;
import com.group6.ads.repositories.database.roles.Role;
import com.group6.ads.repositories.database.roles.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Role> findAll() {
        try {
            logger.info("Find all role");
            return roleRepository.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found role");
        }
    }

    @Override
    public Role createRole(RoleRequest role) {
        try {
            logger.info("Create new role");
            Role roleCreated = Role.builder()
                    .code(role.getCode())
                    .description(role.getDescription())
                    .build();
            return roleRepository.save(roleCreated);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found role");
        }
    }

    @Override
    public Role updateRole(RoleRequest role, Integer theId) {
        try {
            logger.info("Update role");
            Role foundRole = roleRepository.findById(theId).orElseThrow(() -> new NotFoundException("Role not found"));

            foundRole.setCode(role.getCode());
            foundRole.setDescription(role.getDescription());

            return roleRepository.save(foundRole);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found role");
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            logger.info("Delete role");
            if(roleRepository.existsById(id)) {
                roleRepository.deleteById(id);
            }
            else {
                throw new NotFoundException("Role not found");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found role");
        }
    }
}
