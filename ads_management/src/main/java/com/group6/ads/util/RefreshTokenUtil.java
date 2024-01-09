package com.group6.ads.util;

import com.group6.ads.repositories.database.users.User;
import com.group6.ads.repositories.database.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class RefreshTokenUtil {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;

    public String generateRefreshToken(String jwtToken, User currentUser){
        LocalDateTime refreshTokenIssuedTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

        String rfToken = jwtTokenUtil.hashToken(jwtToken);

        // hash then set token for existingStaff entity
        currentUser.setToken(rfToken);
        currentUser.setTokenExpTime(refreshTokenIssuedTime.plusDays(7)); // expired after 7 day

        // save refresh token to DB
        userRepository.save(currentUser);

        return rfToken;
    }

    public Boolean isRefreshTokenExpired(User staff){
        return staff.getTokenExpTime().isBefore(LocalDateTime.now());
    }
}
