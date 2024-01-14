package com.group6.ads.services.properties;

import com.group6.ads.controllers.admin.properties.models.PropertyRequest;
import com.group6.ads.controllers.admin.properties.models.PropertyUpdateRequest;
import com.group6.ads.exceptions.NotFoundException;
import com.group6.ads.repositories.database.properties.Property;
import com.group6.ads.repositories.database.properties.PropertyRepository;
import com.group6.ads.util.PageRequestCustom;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService{
    private final PropertyRepository propertyRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Property save(PropertyRequest properties) {
        try {
            logger.info("Create new property");
            if(Objects.isNull(properties.getPropertyParentId())) {
                Property propertyCreated = Property.builder()
                        .propertyParent(null)
                        .code(properties.getCode())
                        .name(properties.getName())
                        .build();
                return propertyRepository.save(propertyCreated);
            }
            Property propertyParent = propertyRepository.findById(properties.getPropertyParentId())
                    .orElseThrow(() -> new NotFoundException("Property not found"));
            Property propertyCreated = Property.builder()
                    .propertyParent(propertyParent)
                    .code(properties.getCode())
                    .name(properties.getName())
                    .build();
            return propertyRepository.save(propertyCreated);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found property");
        }
    }


    @Override
    public Page<Property> findAllByPropertyParentId(Integer propertyParentId, String search, PageRequestCustom pageRequestCustom) {
        try {
            logger.info("Find all property");
            return propertyRepository.findAllByPropertyParentId(propertyParentId, search, pageRequestCustom.pageRequest());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found property");
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            logger.info("Delete property");
            if(propertyRepository.existsByPropertyParentId(id)) {
                throw new NotFoundException("Property has child");
            }
            propertyRepository.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found property");
        }
    }

    @Override
    public Property findPropertyByWardDistrictAddress(String ward, String district) {
        logger.info("Find Property By Ward District Address");
        Property property =  propertyRepository.findPropertyByWardDistrictAddress(ward, district);
        if (Objects.isNull(property)) {
            throw new NotFoundException("Property not found");
        }
        return property;
    }

    @Override
    public Property update(Integer id, PropertyUpdateRequest propertyRequest) {
        try {
            logger.info("Update property");
            Property property = propertyRepository.findById(id).orElseThrow(() -> new NotFoundException("Property not found"));

            property.setCode(propertyRequest.getCode());
            property.setName(propertyRequest.getName());
            return propertyRepository.save(property);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found property");
        }
    }

    @Override
    public Page<Property> findAllDistrict(String search, PageRequestCustom pageRequestCustom) {
        try {
            logger.info("Find all district");
            return propertyRepository.findAllDistrict(search, pageRequestCustom.pageRequest());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found property");
        }
    }
}
