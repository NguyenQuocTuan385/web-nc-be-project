package com.group6.ads.services.properties;

import com.group6.ads.controllers.properties.models.PropertyRequest;

import java.util.List;
import com.group6.ads.repositories.database.properties.Property;

public interface PropertyService {
    Property save(PropertyRequest properties);

    List<Property> findAllByPropertyParentId(Integer propertyParentId);

   void delete(Integer id);

    Property update(Integer id, PropertyRequest propertyRequest);
}
