package com.group6.ads.repositories.database.locations;

import com.group6.ads.controllers.locations.models.LocationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Query(value = "SELECT * FROM Locations", nativeQuery = true)
    List<Location> findAllWithDetails();

    Optional<LocationDetails> findLocationsById(Integer id);
}
