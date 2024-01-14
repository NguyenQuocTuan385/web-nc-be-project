package com.group6.ads.services.location.types;

import com.group6.ads.controllers.admin.location.types.models.LocationTypeRequest;
import com.group6.ads.exceptions.NotFoundException;
import com.group6.ads.repositories.database.location.types.LocationType;
import com.group6.ads.repositories.database.location.types.LocationTypeRepository;
import com.group6.ads.util.PageRequestCustom;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * com.group6.ads.services.location.types
 * Create by Dang Ngoc Tien
 * Date 12/19/2023 - 10:32 PM
 * Description: ...
 */
@Service
@RequiredArgsConstructor
public class LocationTypeServiceImpl implements LocationTypeService{
    @NonNull
    final LocationTypeRepository locationTypeRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public LocationType create(LocationTypeRequest locationTypeRequest) {
        try {
            logger.info("Create new location type");
            LocationType newLocationType = LocationType.builder()
                    .name(locationTypeRequest.getName())
                    .description(locationTypeRequest.getDescription())
                    .createdAt(LocalDateTime.now())
                    .build();
            return locationTypeRepository.save(newLocationType);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found location type");
        }
    }

    @Override
    public Page<LocationType> findAll(String search, PageRequestCustom pageRequestCustom) {
        try {
            logger.info("Find all location type");
            return locationTypeRepository.findAll(search, pageRequestCustom.pageRequest());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found location type");
        }
    }

    @Override
    public LocationType update(Integer id, LocationTypeRequest locationTypeRequest) {
        try {
            logger.info("Update location type");
            LocationType locationType = locationTypeRepository
                    .findById(id)
                    .orElseThrow(() -> new NotFoundException("Location type not found"));
            locationType.setName(locationTypeRequest.getName());
            locationType.setDescription(locationTypeRequest.getDescription());
            return locationTypeRepository.save(locationType);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found location type");
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            logger.info("Delete location type");
            locationTypeRepository.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found location type");
        }
    }
}
