package com.group6.ads.services.locations;

import com.group6.ads.controllers.locations.models.LocationCreateRequest;
import com.group6.ads.controllers.locations.models.LocationEditByRootRequest;
import com.group6.ads.controllers.locations.models.LocationEditRequest;
import com.group6.ads.repositories.database.advertise.forms.AdvertiseForm;
import com.group6.ads.repositories.database.advertise.forms.AdvertiseFormRepository;
import com.group6.ads.repositories.database.location.types.LocationType;
import com.group6.ads.repositories.database.location.types.LocationTypeRepository;
import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.repositories.database.locations.LocationRepository;
import com.group6.ads.repositories.database.locations.edit.LocationEdit;
import com.group6.ads.repositories.database.locations.edit.LocationEditRepository;
import com.group6.ads.repositories.database.properties.Property;
import com.group6.ads.repositories.database.properties.PropertyRepository;
import com.group6.ads.repositories.database.users.User;
import com.group6.ads.repositories.database.users.UserRepository;
import com.group6.ads.util.PageRequestCustom;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    @NonNull
    final LocationEditRepository locationEditRepository;
    @NonNull
    final LocationRepository locationRepository;
    @NonNull
    final UserRepository userRepository;

    @Override
    public Page<Location> findAll(String search, PageRequestCustom pageRequestCustom) {
        return locationsRepository.findAll(search, pageRequestCustom.pageRequest());
    }

    @Override
    public Page<Location> getAllByPropertyId(Integer propertyId, String search, PageRequestCustom pageRequestCustom) {
        return locationsRepository.findAllByPropertyId(propertyId, search, pageRequestCustom.pageRequest());
    }

    public Location create(LocationCreateRequest locationCreateRequest) {
        AdvertiseForm advertiseForm = advertiseFormRepository.findById(locationCreateRequest.getAdvertiseFormId())
                .orElseThrow(() -> new RuntimeException("Advertise form not found"));
        LocationType locationType = locationTypeRepository.findById(locationCreateRequest.getLocationTypeId())
                .orElseThrow(() -> new RuntimeException("Location type not found"));
        Property property = propertyRepository.findById(locationCreateRequest.getPropertyId())
                .orElseThrow(() -> new RuntimeException("Property not found"));

        Location location = Location.builder()
                .planning(locationCreateRequest.getPlanning())
                .latitude(locationCreateRequest.getLatitude())
                .longitude(locationCreateRequest.getLongitude())
                .address(locationCreateRequest.getAddress())
                .statusEdit(false)
                .adsForm(advertiseForm)
                .locationType(locationType)
                .property(property)
                .createdAt(LocalDateTime.now())
                .images(locationCreateRequest.getImageUrls())
                .build();

        return locationsRepository.save(location);
    }

    @Override
    public void delete(Integer locationId) {
        locationsRepository.deleteById(locationId);
    }

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
                .planning(locationEditRequest.getPlanning())
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
                .images(locationEditRequest.getImageUrls())
                .build();

        locationEditRepository.save(locationEdit);

        location.setStatusEdit(true);
        location.setLocationEdit(locationEdit);
        location.setUpdatedAt(LocalDateTime.now());
        locationRepository.save(location);

        return locationEdit;
    }

    @Override
    public Location updateByRoot(Integer locationId, LocationEditByRootRequest locationEditByRootRequest) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));

        Property property = propertyRepository.findById(locationEditByRootRequest.getPropertyId())
                .orElseThrow(() -> new RuntimeException("Property not found"));
        AdvertiseForm advertiseForm = advertiseFormRepository.findById(locationEditByRootRequest.getAdvertiseFormId())
                .orElseThrow(() -> new RuntimeException("Advertise form not found"));
        LocationType locationType = locationTypeRepository.findById(locationEditByRootRequest.getLocationTypeId())
                .orElseThrow(() -> new RuntimeException("Location type not found"));

        location.setPlanning(locationEditByRootRequest.getPlanning());
        location.setLatitude(locationEditByRootRequest.getLatitude());
        location.setLongitude(locationEditByRootRequest.getLongitude());
        location.setAddress(locationEditByRootRequest.getAddress());
        location.setUpdatedAt(LocalDateTime.now());
        location.setProperty(property);
        location.setAdsForm(advertiseForm);
        location.setLocationType(locationType);
        location.setImages(locationEditByRootRequest.getImageUrls());

        return locationRepository.save(location);
    }

    @Override
    public Page<Location> findAllLocationReview(String search, PageRequestCustom pageRequestCustom) {
        return locationsRepository.findAllLocationReview(search, pageRequestCustom.pageRequest());
    }

    @Override
    public Location locationReview(Integer locationId, Boolean review) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        if (review) {
            Location locationNew = Location.builder()
                    .id(location.getId())
                    .planning(location.getLocationEdit().getPlanning())
                    .latitude(location.getLocationEdit().getLatitude())
                    .longitude(location.getLocationEdit().getLongitude())
                    .address(location.getLocationEdit().getAddress())
                    .statusEdit(false)
                    .locationEdit(null)
                    .adsForm(location.getLocationEdit().getAdsForm())
                    .locationType(location.getLocationEdit().getLocationType())
                    .property(location.getLocationEdit().getProperty())
                    .createdAt(location.getCreatedAt())
                    .updatedAt(LocalDateTime.now())
                    .images(location.getLocationEdit().getImages())
                    .build();

            return locationRepository.save(locationNew);
        }
        location.setStatusEdit(false);
        location.setLocationEdit(null);

        return locationRepository.save(location);
    }
}
