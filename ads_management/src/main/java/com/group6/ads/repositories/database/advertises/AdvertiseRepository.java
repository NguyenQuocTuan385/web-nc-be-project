package com.group6.ads.repositories.database.advertises;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdvertiseRepository extends JpaRepository<Advertise, Integer> {
    List<Advertise> findAllByLocationId(Integer locationId);
}
