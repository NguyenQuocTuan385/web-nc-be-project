package com.group6.ads.repositories.database.advertise.forms;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * com.group6.ads.repositories.database.advertise.forms
 * Create by Dang Ngoc Tien
 * Date 12/17/2023 - 12:48 AM
 * Description: ...
 */
@Repository
public interface AdvertiseFormRepository extends JpaRepository<AdvertiseForm, Integer> {
    @Query("SELECT af FROM AdvertiseForm af WHERE af.name LIKE %:search% OR af.description LIKE %:search%")
    Page<AdvertiseForm> findAll(String search, Pageable pageable);
}
