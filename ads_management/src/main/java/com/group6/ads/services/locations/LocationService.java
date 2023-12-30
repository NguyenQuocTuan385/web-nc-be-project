package com.group6.ads.services.locations;

import com.group6.ads.controllers.locations.models.LocationCreateRequest;
import com.group6.ads.controllers.locations.models.LocationEditByRootRequest;
import com.group6.ads.controllers.locations.models.LocationEditRequest;
import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.repositories.database.locations.edit.LocationEdit;
import com.group6.ads.util.PageRequestCustom;
import org.springframework.data.domain.Page;


public interface LocationService {
    Page<Location> findAll(String search, PageRequestCustom pageRequestCustom);

    Location create(LocationCreateRequest locationCreateRequest);


    Page<Location> getAllByPropertyId(Integer propertyId,String search, PageRequestCustom pageRequestCustom);

    void delete(Integer locationId);

    LocationEdit update(Integer locationId, LocationEditRequest locationEditRequest);

    Location updateByRoot(Integer locationId, LocationEditByRootRequest locationEditByRootRequest);

    Page<Location> findAllLocationReview(String search, PageRequestCustom pageRequestCustom);

    Location locationReview(Integer locationId, Boolean review);

    Location getLocationById(Integer locationId);
}
