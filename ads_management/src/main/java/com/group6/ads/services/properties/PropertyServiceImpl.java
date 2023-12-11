package com.group6.ads.services.properties;

import com.group6.ads.controllers.properties.models.PropertyRequest;
import com.group6.ads.repositories.database.properties.Property;
import com.group6.ads.repositories.database.properties.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService{
    private final PropertyRepository propertyRepository;
    @Override
    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    @Override
    public Property createProperties(PropertyRequest properties) {
        Property propertyCreated = Property.builder()
                .propertyParentId(properties.getPropertyParentId())
                .code(properties.getCode())
                .name(properties.getName())
                .build();
        return propertyRepository.save(propertyCreated);
    }
}
