package com.group6.ads.services.location.types;

import com.group6.ads.controllers.location.types.models.LocationTypeRequest;
import com.group6.ads.repositories.database.location.types.LocationType;
import com.group6.ads.util.PageRequestCustom;
import org.springframework.data.domain.Page;

/**
 * com.group6.ads.services.location.types
 * Create by Dang Ngoc Tien
 * Date 12/19/2023 - 10:33 PM
 * Description: ...
 */
public interface LocationTypeService {
    LocationType create(LocationTypeRequest locationTypeRequest);

    Page<LocationType> findAll(String search, PageRequestCustom pageRequestCustom);

    LocationType update(Integer id, LocationTypeRequest locationTypeRequest);

    void delete(Integer id);
}
