package com.group6.ads.services.users;

import com.group6.ads.controllers.users.models.UserRequest;
import com.group6.ads.repositories.database.users.User;
import com.group6.ads.util.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    Page<User> findAll(Integer roleId, String search , PageRequestCustom pageRequestCustom);

    User findById(Integer id);
    User createUser(UserRequest user);

    User updateUser(UserRequest user, Integer theId);

    void delete(Integer id);
}