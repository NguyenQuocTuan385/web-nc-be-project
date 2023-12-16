package com.group6.ads.services.users;

import com.group6.ads.controllers.users.models.UserCreateDTO;
import com.group6.ads.repositories.database.users.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User createUsers(UserCreateDTO users);
}
