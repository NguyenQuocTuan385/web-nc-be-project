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

    @Query("""
            SELECT ads
            FROM Advertise ads
        WHERE ads.statusEdit = true and (ads.location.property.id = :propertyId or :propertyId is null) and (ads.location.property.propertyParent.id = :parentId or :parentId is null) 
                AND (ads.adsType.name LIKE %:search% OR ads.location.address LIKE %:search%) AND (ads.advertiseEdit is not null)
            """)
    Page<Advertise> findAllUnauthorizedAdvertisements(Integer propertyId,Integer parentId,String search, Pageable pageable);
           
    @Query("""
            SELECT ads
            FROM Advertise ads
            WHERE (ads.adsType.name LIKE %:search% OR ads.location.address LIKE %:search%)
    """)
    Page<Advertise> findAll(String search, Pageable pageable);
}
