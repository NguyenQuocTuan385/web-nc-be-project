package com.group6.ads.services.users;

import com.group6.ads.controllers.users.models.UserCreateDTO;
import com.group6.ads.exceptions.NotFoundException;
import com.group6.ads.repositories.database.properties.Property;
import com.group6.ads.repositories.database.properties.PropertyRepository;
import com.group6.ads.repositories.database.roles.RoleRepository;
import com.group6.ads.repositories.database.roles.Roles;
import com.group6.ads.repositories.database.users.Users;
import com.group6.ads.repositories.database.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository UserRepository;
    private final PropertyRepository propertyRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<Users> findAll() {
        return UserRepository.findAll();
    }

    @Override
    public Users createUsers(UserCreateDTO users) {
        Property property=propertyRepository.findById(users.getPropertyId()).orElse(null);
        Roles roles=roleRepository.findById(users.getRoleId()).orElse(null);
        Users usersCreated = Users.builder()
                .name(users.getName())
                .password(users.getPassword())
                .email(users.getEmail())
                .phone(users.getPhone())
                .birthday(users.getBirthday())
                .avatar(users.getAvatar())
                .property(property)
                .roles(roles)
                .build();
        return UserRepository.save(usersCreated);
    }

    @Override
    public Users updateUsers(UserCreateDTO users, Integer theId) {
        Users foundUsers = UserRepository.findById(theId).orElse(null);

        if (foundUsers == null) {
            throw new NotFoundException("Cannot find actor with id = " + theId);
        }
        foundUsers.setName(users.getName());
        foundUsers.setPassword(users.getPassword());
        foundUsers.setEmail(users.getEmail());
        foundUsers.setPhone(users.getPhone());
        foundUsers.setBirthday(users.getBirthday());
        foundUsers.setAvatar(users.getAvatar());
        foundUsers.setProperty(propertyRepository.findById(users.getPropertyId()).orElse(null));
        foundUsers.setRoles(roleRepository.findById(users.getRoleId()).orElse(null));

        return UserRepository.save(foundUsers);
    }
}
