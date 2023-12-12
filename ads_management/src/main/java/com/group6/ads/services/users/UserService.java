package com.group6.ads.services.users;

import com.group6.ads.controllers.users.models.UserCreateDTO;
import com.group6.ads.repositories.database.users.Users;

import java.util.List;

public interface UserService {
    List<Users> findAll();
    Users createUsers(UserCreateDTO users);
    Users updateUsers(UserCreateDTO users, Integer theId);

}
