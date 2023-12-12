package com.group6.ads.services.properties;

import com.group6.ads.controllers.properties.models.PropertyRequest;

import java.util.List;
import com.group6.ads.repositories.database.properties.Property;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface PropertyService {
    Property save(PropertyRequest properties);

    List<Property> findAllByPropertyParentId(Integer propertyParentId);

   void delete(Integer id);

    Property update(Integer id, PropertyRequest propertyRequest);
}
