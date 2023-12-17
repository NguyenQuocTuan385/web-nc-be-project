package com.group6.ads.repositories.database.advertise.types;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * com.group6.ads.repositories.database.advertise.types
 * Create by Dang Ngoc Tien
 * Date 12/12/2023 - 11:31 PM
 * Description: ...
 */
@Repository
public interface AdvertiseTypeRepository extends JpaRepository<AdvertiseType, Integer> {
    @Query("SELECT at FROM AdvertiseType at WHERE at.name LIKE %:search% OR at.description LIKE %:search%")
    Page<AdvertiseType> findAll(String search, Pageable pageRequest);
}
