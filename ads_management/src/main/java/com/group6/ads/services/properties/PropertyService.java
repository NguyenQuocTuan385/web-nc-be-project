package com.group6.ads.services.properties;

import com.group6.ads.controllers.properties.models.PropertyRequest;

import java.util.List;
import com.group6.ads.repositories.database.properties.Property;
import org.springframework.stereotype.Service;


public interface PropertyService {
    List<Property> findAll();
    Property createProperties(PropertyRequest properties);
}
