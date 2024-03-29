package com.group6.ads.repositories.database.properties;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
    Boolean existsByPropertyParentId(Integer propertyParentId);

    @Query("""
            SELECT p
            FROM Property p
            WHERE p.propertyParent.id = :propertyParentId 
                AND (p.code LIKE %:search% OR p.name LIKE %:search%)
            """)
    Page<Property> findAllByPropertyParentId(Integer propertyParentId, String search, Pageable pageable);


    @Query("""
            SELECT p
            FROM Property p
            WHERE p.propertyParent.id IS NULL
                AND (p.code LIKE %:search% OR p.name LIKE %:search%)
            """)
    Page<Property> findAllDistrict(String search, Pageable pageable);

    @Query("""
            SELECT p
            FROM Property p
            WHERE p.name LIKE %:ward% AND p.propertyParent.name LIKE %:district%
            """)
    Property findPropertyByWardDistrictAddress(String ward, String district);
}
