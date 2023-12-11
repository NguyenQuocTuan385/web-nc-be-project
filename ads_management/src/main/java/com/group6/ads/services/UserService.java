package com.group6.ads.services;

import com.group6.ads.dtos.UserCreateDTO;
import com.group6.ads.entities.Users;

import java.util.List;

public interface UserService {
    List<Users> findAll();
    Users createUsers(UserCreateDTO users);
}
