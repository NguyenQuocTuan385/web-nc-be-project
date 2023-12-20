package com.group6.ads.services.location.types;

import com.group6.ads.controllers.location.types.models.LocationTypeRequest;
import com.group6.ads.exceptions.NotFoundException;
import com.group6.ads.repositories.database.location.types.LocationType;
import com.group6.ads.repositories.database.location.types.LocationTypeRepository;
import com.group6.ads.util.PageRequestCustom;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
    @Override
    public LocationType create(LocationTypeRequest locationTypeRequest) {
        LocationType newLocationType = LocationType.builder()
                .name(locationTypeRequest.getName())
                .description(locationTypeRequest.getDescription())
                .createdAt(LocalDateTime.now())
                .build();
        return locationTypeRepository.save(newLocationType);
    }

    @Override
    public Page<LocationType> findAll(String search, PageRequestCustom pageRequestCustom) {
        return locationTypeRepository.findAll(search, pageRequestCustom.pageRequest());
    }

    @Override
    public LocationType update(Integer id, LocationTypeRequest locationTypeRequest) {
        LocationType locationType = locationTypeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Location type not found"));
        locationType.setName(locationTypeRequest.getName());
        locationType.setDescription(locationTypeRequest.getDescription());
        return locationTypeRepository.save(locationType);
    }

    @Override
    public void delete(Integer id) {
        locationTypeRepository.deleteById(id);
    }
}
