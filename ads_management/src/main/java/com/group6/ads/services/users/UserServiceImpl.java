package com.group6.ads.services.users;

import com.group6.ads.controllers.users.models.UserOTPRequest;
import com.group6.ads.controllers.users.models.UserRequest;
import com.group6.ads.exceptions.NotFoundException;
import com.group6.ads.repositories.database.properties.Property;
import com.group6.ads.repositories.database.properties.PropertyRepository;
import com.group6.ads.repositories.database.roles.Role;
import com.group6.ads.repositories.database.roles.RoleRepository;
import com.group6.ads.repositories.database.users.UserRepository;
import com.group6.ads.repositories.database.users.User;
import com.group6.ads.util.PageRequestCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository UserRepository;
    private final PropertyRepository propertyRepository;
    private final RoleRepository roleRepository;

    @Override
    public Page<User> findAll(Integer roleId, String search, PageRequestCustom pageRequestCustom) {
        return roleId == null ? UserRepository.findAll(search,pageRequestCustom.pageRequest()) :
                UserRepository.findAll(roleId,search,pageRequestCustom.pageRequest());
    }

    @Override
    public User findById(Integer id) {
        return UserRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public User createUser(UserRequest user) {
        Property property = propertyRepository.findById(user.getPropertyId()).orElse(null);
        Role role = roleRepository.findById(user.getRoleId()).orElse(null);
        User usersCreated = User.builder()
                .name(user.getName())
                .password(user.getPassword())
                .email(user.getEmail())
                .phone(user.getPhone())
                .birthday(user.getBirthday())
                .avatar(user.getAvatar())
                .property(property)
                .role(role)
                .build();
        return UserRepository.save(usersCreated);
    }

    @Override
    public User updateUser(UserRequest user, Integer theId) {
        User foundUsers = UserRepository.findById(theId).orElseThrow(() -> new NotFoundException("User not found"));

        foundUsers.setName(user.getName());
        if(user.getPassword() != null
                && !user.getPassword().isEmpty()
                && !user.getPassword().equals("null"))
        {
            foundUsers.setPassword(user.getPassword());
        }
        foundUsers.setEmail(user.getEmail());
        foundUsers.setPhone(user.getPhone());
        foundUsers.setBirthday(user.getBirthday());
        foundUsers.setAvatar(user.getAvatar());
        foundUsers.setProperty(propertyRepository.findById(user.getPropertyId()).orElse(null));
        foundUsers.setRole(roleRepository.findById(user.getRoleId()).orElse(null));

        return UserRepository.save(foundUsers);
    }

    @Override
    public Integer checkOTP(UserOTPRequest userOtpRequest) {
        User user = UserRepository.findByEmail(userOtpRequest.getEmail()).orElseThrow(() -> new NotFoundException("User not found"));
       if(!Objects.equals(user.getOtp(), userOtpRequest.getOtp()))
       {
           return 0;
       }
       else
     {
          if(user.getOtpExpTime().isBefore(LocalDateTime.now()))
          {
            return 1;
          }
          else
          {
            return 2;
          }
     }

    }

    @Override
    public void delete(Integer id) {
        if (UserRepository.existsById(id)) {
            UserRepository.deleteById(id);
        } else {
            throw new NotFoundException("User not found");
        }
    }
}