package com.group6.ads.services.advertises;

import com.group6.ads.controllers.advertises.models.AdvertiseLicensingRequest;
import com.group6.ads.controllers.advertises.models.AdvertiseRequest;
import com.group6.ads.exceptions.NotFoundException;
import com.group6.ads.repositories.database.advertise.types.AdvertiseType;
import com.group6.ads.repositories.database.advertise.types.AdvertiseTypeRepository;
import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.repositories.database.advertises.AdvertiseRepository;
import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.repositories.database.locations.LocationRepository;
import com.group6.ads.repositories.database.properties.Property;
import com.group6.ads.util.PageRequestCustom;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
public class AdvertiseServiceImpl implements AdvertiseService {
    @NonNull
    final AdvertiseRepository advertiseRepository;
    @NonNull
    final AdvertiseTypeRepository advertiseTypeRepository;
    @NonNull
    final LocationRepository locationRepository;

    @Override
    public Page<Advertise> findAllByLocationId(Integer locationId, String search, PageRequestCustom pageRequestCustom) {
        return advertiseRepository.findAllByLocationId(locationId, search, pageRequestCustom.pageRequest());
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
                .images(advertiseRequest.getImages())
                .statusEdit(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return advertiseRepository.save(newAdvertise);
    }

    @Override
    public Advertise updateByRoot(Integer advertiseId, AdvertiseRequest advertiseRequest) {
        AdvertiseType advertiseType = advertiseTypeRepository
                .findById(advertiseRequest.getAdsTypeId())
                .orElseThrow(() -> new NotFoundException("Advertise type not found"));
        Advertise advertise = advertiseRepository
                .findById(advertiseId)
                .orElseThrow(() -> new NotFoundException("Advertise not found"));

        advertise.setLicensing(advertiseRequest.getLicensing());
        advertise.setHeight(advertiseRequest.getHeight());
        advertise.setWidth(advertiseRequest.getWidth());
        advertise.setAdsType(advertiseType);
        advertise.setImages(advertiseRequest.getImages());
        advertise.setStatusEdit(false);
        advertise.setUpdatedAt(LocalDateTime.now());

        return advertiseRepository.save(advertise);
    }

    @Override
    public Advertise updateLicense(Integer advertiseId, AdvertiseLicensingRequest advertiseRequest) {
        Advertise advertise = advertiseRepository
                .findById(advertiseId)
                .orElseThrow(() -> new NotFoundException("Advertise not found"));
        advertise.setLicensing(advertiseRequest.getLicensing());
        advertise.setUpdatedAt(LocalDateTime.now());
        return advertiseRepository.save(advertise);

    }

    @Override
    public void delete(Integer advertiseId) {
        Advertise advertise = advertiseRepository
                .findById(advertiseId)
                .orElseThrow(() -> new NotFoundException("Advertise not found"));
        advertiseRepository.delete(advertise);
    }
}
