package com.group6.ads.services.location.edit;

import com.group6.ads.controllers.location.edit.models.LocationEditRequest;
import com.group6.ads.repositories.database.advertise.forms.AdvertiseForm;
import com.group6.ads.repositories.database.advertise.forms.AdvertiseFormRepository;
import com.group6.ads.repositories.database.location.edit.LocationEdit;
import com.group6.ads.repositories.database.location.edit.LocationEditRepository;
import com.group6.ads.repositories.database.location.types.LocationType;
import com.group6.ads.repositories.database.location.types.LocationTypeRepository;
import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.repositories.database.locations.LocationRepository;
import com.group6.ads.repositories.database.properties.Property;
import com.group6.ads.repositories.database.properties.PropertyRepository;
import com.group6.ads.repositories.database.users.User;
import com.group6.ads.repositories.database.users.UserRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class LocationEditServiceImpl implements LocationEditService {
    @NonNull
    final LocationEditRepository locationEditRepository;
    @NonNull
    final LocationRepository locationRepository;
    @NonNull
    final PropertyRepository propertyRepository;
    @NonNull
    final AdvertiseFormRepository advertiseFormRepository;
    @NonNull
    final LocationTypeRepository locationTypeRepository;
    @NonNull
    final UserRepository userRepository;

    @Override
    @Transactional
    public LocationEdit update(Integer locationId, LocationEditRequest locationEditRequest) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));

        Property property = propertyRepository.findById(locationEditRequest.getPropertyId())
                .orElseThrow(() -> new RuntimeException("Property not found"));
        AdvertiseForm advertiseForm = advertiseFormRepository.findById(locationEditRequest.getAdvertiseFormId())
                .orElseThrow(() -> new RuntimeException("Advertise form not found"));
        LocationType locationType = locationTypeRepository.findById(locationEditRequest.getLocationTypeId())
                .orElseThrow(() -> new RuntimeException("Location type not found"));
        User user = userRepository.findById(locationEditRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocationEdit locationEdit = LocationEdit.builder()
                .latitude(locationEditRequest.getLatitude())
                .longitude(locationEditRequest.getLongitude())
                .address(locationEditRequest.getAddress())
                .content(locationEditRequest.getContent())
                .createdAt(location.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .user(user)
                .property(property)
                .adsForm(advertiseForm)
                .locationType(locationType)
                .build();

        locationEditRepository.save(locationEdit);

        location.setStatusEdit(true);
        location.setLocationEdit(locationEdit);
        location.setUpdatedAt(LocalDateTime.now());
        locationRepository.save(location);

        return locationEdit;
    }
}
