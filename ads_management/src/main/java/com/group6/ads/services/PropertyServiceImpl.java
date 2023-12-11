package com.group6.ads.services;

import com.group6.ads.dtos.PropertyCreateDTO;
import com.group6.ads.repositories.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import com.group6.ads.entities.Properties;
@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService{
    private final PropertyRepository propertyRepository;
    @Override
    public List<Properties> findAll() {
        return propertyRepository.findAll();
    }

    @Override
    public Properties createProperties(PropertyCreateDTO properties) {
        Properties propertiesCreated = Properties.builder()
                .property_parent_id(properties.getProperty_parent_id())
                .code(properties.getCode())
                .name(properties.getName())
                .build();
        return propertyRepository.save(propertiesCreated);
    }


}
