package com.group6.ads.repositories.database.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("""
            SELECT u
            FROM User u
            WHERE u.role.id = :roleId and (u.name LIKE %:search% OR u.email LIKE %:search% OR u.phone LIKE %:search%)
            """)
    Page<User> findAll(Integer roleId, String search, Pageable pageable);

    Optional<User> findByEmail(String email);

    @Query("""
            SELECT u
            FROM User u
            WHERE (u.name LIKE %:search% OR u.email LIKE %:search% OR u.phone LIKE %:search%)
            """)
    Page<User> findAll(String search, Pageable pageable);
}
