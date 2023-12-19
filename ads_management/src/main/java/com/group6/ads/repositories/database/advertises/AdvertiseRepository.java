package com.group6.ads.repositories.database.advertises;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvertiseRepository extends JpaRepository<Advertise, Integer> {
    List<Advertise> findAllByLocationId(Integer locationId);

    @Query("SELECT a FROM Advertise a WHERE a.id = :advertiseId")
    Optional<Advertise> findById(Integer advertiseId);
}
