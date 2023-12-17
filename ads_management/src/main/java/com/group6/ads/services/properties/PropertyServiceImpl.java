package com.group6.ads.services.properties;

import com.group6.ads.controllers.properties.models.PropertyRequest;
import com.group6.ads.repositories.database.properties.Property;
import com.group6.ads.repositories.database.properties.PropertyRepository;
import com.group6.ads.util.PageRequestCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService{
    private final PropertyRepository propertyRepository;

    @Override
    public Property save(PropertyRequest properties) {
        Property propertyCreated = Property.builder()
                .propertyParentId(properties.getPropertyParentId())
                .code(properties.getCode())
                .name(properties.getName())
                .build();
        return propertyRepository.save(propertyCreated);
    }

    @Override
    public Page<Property> findAllByPropertyParentId(Integer propertyParentId, String search, PageRequestCustom pageRequestCustom) {
        return propertyRepository.findAllByPropertyParentId(propertyParentId, search, pageRequestCustom.pageRequest());
    }

    @Override
    public void delete(Integer id) {
        if(propertyRepository.existsByPropertyParentId(id)) {
            throw new RuntimeException("Property has child");
        }
        propertyRepository.deleteById(id);
    }

    @Override
    public Property update(Integer id, PropertyRequest propertyRequest) {
        Property property = propertyRepository.findById(id).orElseThrow(() -> new RuntimeException("Property not found"));
        property.setPropertyParentId(propertyRequest.getPropertyParentId());
        property.setCode(propertyRequest.getCode());
        property.setName(propertyRequest.getName());
        return propertyRepository.save(property);
    }
}
