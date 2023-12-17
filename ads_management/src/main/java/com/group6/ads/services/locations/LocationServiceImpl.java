package com.group6.ads.services.locations;

import com.group6.ads.controllers.locations.models.LocationCreateRequest;
import com.group6.ads.repositories.database.advertise.forms.AdvertiseForm;
import com.group6.ads.repositories.database.advertise.forms.AdvertiseFormRepository;
import com.group6.ads.repositories.database.location.types.LocationType;
import com.group6.ads.repositories.database.location.types.LocationTypeRepository;
import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.repositories.database.locations.LocationRepository;
import com.group6.ads.repositories.database.properties.Property;
import com.group6.ads.repositories.database.properties.PropertyRepository;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    @NonNull
    final LocationRepository locationsRepository;
    @NotNull
    final LocationTypeRepository locationTypeRepository;
    @NotNull
    final PropertyRepository propertyRepository;
    @NotNull
    final AdvertiseFormRepository advertiseFormRepository;

    @Override
    public List<Location> findAll() {
        return locationsRepository.findAll();
    }

    @Override
    public List<Location> getAllByPropertyId(Integer propertyId) {
        return locationsRepository.findAllByPropertyId(propertyId);
    }

    public Location create(LocationCreateRequest locationCreateRequest) {
        AdvertiseForm advertiseForm = advertiseFormRepository.findById(locationCreateRequest.getAdvertiseFormId())
                .orElseThrow(() -> new RuntimeException("Advertise form not found"));
        LocationType locationType = locationTypeRepository.findById(locationCreateRequest.getLocationTypeId())
                .orElseThrow(() -> new RuntimeException("Location type not found"));
        Property property = propertyRepository.findById(locationCreateRequest.getPropertyId())
                .orElseThrow(() -> new RuntimeException("Property not found"));

        Location location = Location.builder()
                .latitude(locationCreateRequest.getLatitude())
                .longitude(locationCreateRequest.getLongitude())
                .address(locationCreateRequest.getAddress())
                .statusEdit(false)
                .adsForm(advertiseForm)
                .locationType(locationType)
                .property(property)
                .createdAt(LocalDateTime.now())
                .build();

        return locationsRepository.save(location);
    }

    @Override
    public void delete(Integer locationId) {
        locationsRepository.deleteById(locationId);
    }
}
