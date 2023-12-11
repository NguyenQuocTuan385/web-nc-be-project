package com.group6.ads.services;

import com.group6.ads.dtos.UserCreateDTO;
import com.group6.ads.entities.Users;
import com.group6.ads.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository UserRepository;

    @Override
    public List<Users> findAll() {
        return UserRepository.findAll();
    }

    @Override
    public Users createUsers(UserCreateDTO users) {
        Users usersCreated = Users.builder()
                .name(users.getName())
                .password(users.getPassword())
                .email(users.getEmail())
                .phone(users.getPhone())
                .birthday(users.getBirthday())
                .avatar(users.getAvatar())
                .roles(users.getRoles())
                .properties(users.getProperties())
                .build();
        return UserRepository.save(usersCreated);

    }
}
