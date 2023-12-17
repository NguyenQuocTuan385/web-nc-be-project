package com.group6.ads.services.users;

import com.group6.ads.controllers.users.models.UserRequest;
import com.group6.ads.repositories.database.users.Users;

import java.util.List;

public interface UserService {
    List<Users> findAll();
    Users createUsers(UserRequest users);
    Users updateUsers(UserRequest users, Integer theId);

}
