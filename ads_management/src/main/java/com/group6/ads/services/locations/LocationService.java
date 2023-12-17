package com.group6.ads.services.locations;

import com.group6.ads.controllers.locations.models.LocationDetails;
import com.group6.ads.repositories.database.locations.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    public List<Location> getAllLocationsWithDetails();
    public Optional<LocationDetails> getLocationById(Integer locationId);
}
