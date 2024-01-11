package com.group6.ads.services.users;

import com.group6.ads.controllers.users.models.UserGuestRequest;
import com.group6.ads.controllers.users.models.UserOTPRequest;
import com.group6.ads.controllers.users.models.UserRequest;
import com.group6.ads.repositories.database.users.User;
import com.group6.ads.util.PageRequestCustom;
import org.springframework.data.domain.Page;

public interface UserService {
    Page<User> findAll(Integer roleId, String search , PageRequestCustom pageRequestCustom);

    User findById(Integer id);

    User createUser(UserRequest user);

    User createGuestUser(UserGuestRequest userGuestRequest);

    User updateUser(UserRequest user, Integer theId);

    Integer checkOTP(UserOTPRequest userOtpRequest);
  
    User findByEmail(String email);

    void delete(Integer id);
}