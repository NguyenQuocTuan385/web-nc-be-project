package com.group6.ads.services.locations;

import com.group6.ads.controllers.locations.models.LocationCreateRequest;
import com.group6.ads.controllers.locations.models.LocationEditByRootRequest;
import com.group6.ads.controllers.locations.models.LocationEditRequest;
import com.group6.ads.controllers.locations.models.LocationStatusRequest;
import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.repositories.database.locations.edit.LocationEdit;
import com.group6.ads.util.PageRequestCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface LocationService {
    Page<Location> findAll(String search, PageRequestCustom pageRequestCustom);

    Page<Location> findAll(Integer[] propertyId, Integer[] parentId, String search, PageRequestCustom pageRequestCustom);


    Location create(LocationCreateRequest locationCreateRequest);

    Location getById(Integer locationId);


    Page<Location> getAllByPropertyId(Integer propertyId,String search, PageRequestCustom pageRequestCustom);

    void delete(Integer locationId);
    void deleteLocationEdit(Integer locationEditId);

    Location updateStatus(Integer locationId, LocationStatusRequest locationStatusRequest);

    LocationEdit update(Integer locationId, LocationEditRequest locationEditRequest);

    Location updateByRoot(Integer locationId, LocationEditByRootRequest locationEditByRootRequest);

    Page<Location> findAllLocationReview(Integer propertyId,Integer parentId,String search, PageRequestCustom pageRequestCustom);

    Location locationReview(Integer locationId, Boolean review);

    Location getLocationById(Integer locationId);
}
