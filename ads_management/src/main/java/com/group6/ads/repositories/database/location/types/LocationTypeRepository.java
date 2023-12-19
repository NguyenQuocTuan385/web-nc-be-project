package com.group6.ads.repositories.database.location.types;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * com.group6.ads.repositories.database.location.types
 * Create by Dang Ngoc Tien
 * Date 12/17/2023 - 12:49 AM
 * Description: ...
 */
@Repository
public interface LocationTypeRepository extends JpaRepository<LocationType, Integer> {
    @Query("SELECT lt FROM LocationType lt WHERE lt.name LIKE %:search% OR lt.description LIKE %:search%")
    Page<LocationType> findAll(String search, Pageable pageable);
}
