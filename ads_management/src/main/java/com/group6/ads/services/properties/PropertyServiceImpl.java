package com.group6.ads.services.properties;

import com.group6.ads.controllers.properties.models.PropertyRequest;
import com.group6.ads.controllers.properties.models.PropertyUpdateRequest;
import com.group6.ads.exceptions.NotFoundException;
import com.group6.ads.repositories.database.properties.Property;
import com.group6.ads.repositories.database.properties.PropertyRepository;
import com.group6.ads.util.PageRequestCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService{
    private final PropertyRepository propertyRepository;

    @Override
    public Property save(PropertyRequest properties) {
        Property propertyParent = propertyRepository.findById(properties.getPropertyParentId())
                .orElseThrow(() -> new NotFoundException("Property not found"));
        Property propertyCreated = Property.builder()
                .propertyParent(propertyParent)
                .code(properties.getCode())
                .name(properties.getName())
                .build();
        return propertyRepository.save(propertyCreated);
    }

    @Override
    public List<Property> findAll() {
        return propertyRepository.findAllByPropertyParentIdIsNull();
    }

    @Override
    public Page<Property> findAllByPropertyParentId(Integer propertyParentId, String search, PageRequestCustom pageRequestCustom) {
        return propertyRepository.findAllByPropertyParentId(propertyParentId, search, pageRequestCustom.pageRequest());
    }

    @Override
    public void delete(Integer id) {
        if(propertyRepository.existsByPropertyParentId(id)) {
            throw new NotFoundException("Property has child");
        }
        propertyRepository.deleteById(id);
    }

    @Override
    public Property update(Integer id, PropertyUpdateRequest propertyRequest) {
        Property property = propertyRepository.findById(id).orElseThrow(() -> new NotFoundException("Property not found"));

        property.setCode(propertyRequest.getCode());
        property.setName(propertyRequest.getName());
        return propertyRepository.save(property);
    }

    @Override
    public Page<Property> findAllDistrict(String search, PageRequestCustom pageRequestCustom) {
        return propertyRepository.findAllDistrict(search, pageRequestCustom.pageRequest());
    }
}
