package com.group6.ads.services.locations;

import com.group6.ads.controllers.locations.models.LocationCreateRequest;
import com.group6.ads.repositories.database.locations.Location;

import java.util.List;

public interface LocationService {
    List<Location> findAll();

    Location create(LocationCreateRequest locationCreateRequest);

    List<Location> getAllByPropertyId(Integer propertyId);

    void delete(Integer locationId);
}
