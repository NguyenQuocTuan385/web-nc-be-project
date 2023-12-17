package com.group6.ads.services.users;

import com.group6.ads.controllers.users.models.UserCreateRequest;
import com.group6.ads.repositories.database.users.User;
import com.group6.ads.repositories.database.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository UserRepository;

    @Override
    public List<User> findAll() {
        return UserRepository.findAll();
    }

    @Override
    public User createUsers(UserCreateRequest users) {
        User userCreated = User.builder()
                .name(users.getName())
                .password(users.getPassword())
                .email(users.getEmail())
                .phone(users.getPhone())
                .birthday(users.getBirthday())
                .avatar(users.getAvatar())
                // .roles(users.getRoles())
                // .property(users.getProperty())
                .build();
        return UserRepository.save(userCreated);

    }
}
