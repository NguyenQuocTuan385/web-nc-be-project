package com.group6.ads.repositories.database.locations;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Query("SELECT l FROM Location l WHERE l.property.id = :propertyId AND l.address LIKE %:search%")
    Page<Location> findAllByPropertyId(Integer propertyId, String search, Pageable pageable);

    @Query("SELECT l FROM Location l WHERE l.address LIKE %:search%")
    Page<Location> findAll(String search, Pageable pageable);

    @Query("""
              SELECT l FROM Location l
              WHERE l.statusEdit = TRUE AND l.address LIKE %:search% 
              AND (l.property.id = :propertyId OR :propertyId IS NULL) 
              AND (l.property.propertyParent.id = :parentId OR :parentId IS NULL)
            """)
    Page<Location> findAllLocationReview(Integer propertyId,Integer parentId,String search, Pageable pageable);
}
