package com.group6.ads.services.properties;

import com.group6.ads.controllers.properties.models.PropertyRequest;

import java.util.List;

import com.group6.ads.repositories.database.properties.Property;
import jakarta.transaction.Transactional;

public interface PropertyService {
    @Transactional
    Property save(PropertyRequest properties);

    List<Property> findAllByPropertyParentId(Integer propertyParentId);

    @Transactional
    void delete(Integer id);

    @Transactional
    Property update(Integer id, PropertyRequest propertyRequest);
}
