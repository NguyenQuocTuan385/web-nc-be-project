package com.group6.ads.services.advertises;

import com.group6.ads.controllers.advertises.models.AdvertiseRequest;
import com.group6.ads.repositories.database.advertise.types.AdvertiseType;
import com.group6.ads.repositories.database.advertise.types.AdvertiseTypeRepository;
import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.repositories.database.advertises.AdvertiseRepository;
import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.repositories.database.locations.LocationRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * com.group6.ads.services.advertise
 * Create by Dang Ngoc Tien
 * Date 12/18/2023 - 11:27 PM
 * Description: ...
 */
@Service
@RequiredArgsConstructor
public class AdvertiseServiceImpl implements AdvertiseService{
    @NonNull
    final AdvertiseRepository advertiseRepository;
    @NonNull
    final AdvertiseTypeRepository advertiseTypeRepository;
    @NonNull
    final LocationRepository locationRepository;
    @Override
    public List<Advertise> findAllByLocationId(Integer locationId) {
        return advertiseRepository.findAllByLocationId(locationId);
    }

    @Override
    public Advertise updateLocation(Integer advertiseId, Integer locationId) {
        return null;
    }

    @Override
    public Advertise create(Integer locationId, AdvertiseRequest advertiseRequest) {
        Location location = locationRepository
                .findById(locationId)
                .orElseThrow();
       AdvertiseType advertiseType = advertiseTypeRepository
               .findById(advertiseRequest.getAdsTypeId())
               .orElseThrow();

        Advertise newAdvertise = Advertise.builder()
                .location(location)
                .licensing(advertiseRequest.getLicensing())
                .height(advertiseRequest.getHeight())
                .width(advertiseRequest.getWidth())
                .adsType(advertiseType)
                .statusEdit(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return advertiseRepository.save(newAdvertise);
    }
}
