package com.group6.ads.services.authentication;

import com.group6.ads.controllers.admin.authentication.models.ChangePasswordRequest;
import com.group6.ads.controllers.admin.authentication.models.RegisterRequest;
import com.group6.ads.controllers.admin.authentication.models.ResetPasswordRequest;
import com.group6.ads.exceptions.NotFoundException;
import com.group6.ads.repositories.database.properties.Property;
import com.group6.ads.repositories.database.properties.PropertyRepository;
import com.group6.ads.repositories.database.roles.Role;
import com.group6.ads.repositories.database.roles.RoleRepository;
import com.group6.ads.repositories.database.users.User;
import com.group6.ads.repositories.database.users.UserRepository;
import com.group6.ads.util.JwtTokenUtil;
import com.group6.ads.util.RefreshTokenUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final RoleRepository roleRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenUtil refreshTokenUtil;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User register(RegisterRequest registerRequest) throws Exception {
        Property property = propertyRepository.findById(registerRequest.getPropertyId()).orElse(null);
        Role role = roleRepository.findById(registerRequest.getRoleId()).orElse(null);
        User usersCreated = User.builder()
                .name(registerRequest.getName())
                .password(registerRequest.getPassword())
                .email(registerRequest.getEmail())
                .phone(registerRequest.getPhone())
                .birthday(registerRequest.getBirthday())
                .avatar(registerRequest.getAvatar())
                .property(property)
                .role(role)
                .build();
        String password = registerRequest.getPassword();
        String encodePassword = passwordEncoder.encode(password);

        usersCreated.setPassword(encodePassword);
        logger.info("User created: {}", usersCreated);
        return userRepository.save(usersCreated);
    }

    @Override
    public User changePassword(ChangePasswordRequest changePasswordRequest) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(changePasswordRequest.getEmail());
        User existingUser = optionalUser.get();

        if(!passwordEncoder.matches(changePasswordRequest.getOldPassword(), existingUser.getPassword()))
            throw new BadCredentialsException("Wrong password");
        String newPassword = changePasswordRequest.getNewPassword();
        String encodePassword = passwordEncoder.encode(newPassword);

        User user= userRepository.findByEmail(changePasswordRequest.getEmail()).orElseThrow(() -> new NotFoundException("User not found"));
        user.setPassword(encodePassword);
        logger.info("User changed password: {}", user);
        return userRepository.save(user);
    }

    @Override
    public User resetPassword(ResetPasswordRequest resetPasswordRequest) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(resetPasswordRequest.getEmail());
        User existingUser = optionalUser.get();

        String newPassword = resetPasswordRequest.getPassword();
        String encodePassword = passwordEncoder.encode(newPassword);

        User user= userRepository.findByEmail(resetPasswordRequest.getEmail()).orElseThrow(() -> new NotFoundException("User not found"));
        user.setPassword(encodePassword);
        logger.info("User reset password: {}", user);
        return userRepository.save(user);
    }

    @Override
    public HashMap<String, String> login(String email, String password) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        String newJwtToken;

        if (optionalUser.isEmpty())
            throw new NotFoundException("Invalid username / password");

        User existingUser = optionalUser.get();

        if (!passwordEncoder.matches(password, existingUser.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                email, password,
                existingUser.getAuthorities()
        );
        authenticationManager.authenticate(authenticationToken);

        newJwtToken = jwtTokenUtil.generateToken(existingUser);

        String rfToken = refreshTokenUtil.generateRefreshToken(newJwtToken, existingUser);

        HashMap<String, String> map = new HashMap<>();
        map.put("access_token", newJwtToken);
        map.put("refresh_token", rfToken);
        map.put("uid", existingUser.getId().toString());

        logger.info("User logged in: {}", existingUser);
        return map;
    }

    @Override
    public HashMap<String, String> refresh(String token) throws Exception {
        User user = userRepository.findByToken(token)
                .orElseThrow(() -> new NotFoundException("No Refresh Token in Database"));

        // check if rf token expired
        if(refreshTokenUtil.isRefreshTokenExpired(user))
            throw new RuntimeException("Refresh Token Expired, please login again");

        // generate new access token
        String newAccessToken = jwtTokenUtil.generateToken(user);

        HashMap<String, String> map = new HashMap<>();
        map.put("access_token", newAccessToken);

        logger.info("User refreshed token: {}", user);
        return map;
    }

    @Override
    public void logout(HttpServletResponse response) throws Exception {
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        logger.info("User logged out");
    }
}
