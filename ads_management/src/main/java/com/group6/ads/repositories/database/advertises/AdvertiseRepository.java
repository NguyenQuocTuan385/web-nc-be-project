package com.group6.ads.repositories.database.advertises;

import com.group6.ads.repositories.database.properties.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdvertiseRepository extends JpaRepository<Advertise, Integer> {
    @Query("""
            SELECT ads
            FROM Advertise ads
            WHERE ads.location.id = :locationId 
                AND (ads.adsType.name LIKE %:search% OR ads.location.address LIKE %:search%)
            """)
    Page<Advertise> findAllByLocationId(Integer locationId, String search, Pageable pageable);
}
