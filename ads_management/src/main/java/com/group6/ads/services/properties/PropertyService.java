package com.group6.ads.services.properties;

import com.group6.ads.controllers.properties.models.PropertyRequest;

import com.group6.ads.repositories.database.properties.Property;
import com.group6.ads.util.PageRequestCustom;
import org.springframework.data.domain.Page;

public interface PropertyService {
    Property save(PropertyRequest properties);

    Page<Property> findAllByPropertyParentId(Integer propertyParentId, String search, PageRequestCustom pageRequestCustom);

    void delete(Integer id);

    Property update(Integer id, PropertyRequest propertyRequest);

    Page<Property> findAllDistrict(String search, PageRequestCustom pageRequestCustom);
}
