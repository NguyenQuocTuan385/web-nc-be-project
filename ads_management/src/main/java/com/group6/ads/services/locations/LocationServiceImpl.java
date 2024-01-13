package com.group6.ads.services.locations;

import com.group6.ads.controllers.admin.locations.models.LocationCreateRequest;
import com.group6.ads.controllers.admin.locations.models.LocationEditByRootRequest;
import com.group6.ads.controllers.admin.locations.models.LocationEditRequest;
import com.group6.ads.controllers.admin.locations.models.LocationStatusRequest;
import com.group6.ads.exceptions.NotFoundException;
import com.group6.ads.repositories.database.advertise.forms.AdvertiseForm;
import com.group6.ads.repositories.database.advertise.forms.AdvertiseFormRepository;
import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.repositories.database.advertises.AdvertiseRepository;
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
    @NotNull
    final LocationTypeRepository locationTypeRepository;
    @NotNull
    final PropertyRepository propertyRepository;
    @NotNull
    final AdvertiseFormRepository advertiseFormRepository;
    @NotNull
    final AdvertiseRepository advertiseRepository;
    @NonNull
    final LocationEditRepository locationEditRepository;
    @NonNull
    final LocationRepository locationRepository;
    @NonNull
    final UserRepository userRepository;

    @Override
    public Page<Location> findAll(String search, PageRequestCustom pageRequestCustom) {
        return locationRepository.findAll(search, pageRequestCustom.pageRequest());
    }

    @Override
    public Page<Location> findAll(Integer[] propertyId, Integer[] parentId, String search, PageRequestCustom pageRequestCustom) {
        if(propertyId.length == 0)
            propertyId = null;
        if(parentId.length == 0)
            parentId = null;

        return locationRepository.findAll(propertyId, parentId, search, pageRequestCustom.pageRequest());
    }

    @Override
    public Page<Location> getAllByPropertyId(Integer propertyId, String search, PageRequestCustom pageRequestCustom) {
        return locationRepository.findAllByPropertyId(propertyId, search, pageRequestCustom.pageRequest());
    }

    public Location create(LocationCreateRequest locationCreateRequest) {
        AdvertiseForm advertiseForm = advertiseFormRepository.findById(locationCreateRequest.getAdvertiseFormId())
                .orElseThrow(() -> new NotFoundException("Advertise form not found"));
        LocationType locationType = locationTypeRepository.findById(locationCreateRequest.getLocationTypeId())
                .orElseThrow(() -> new NotFoundException("Location type not found"));
        Property property = propertyRepository.findById(locationCreateRequest.getPropertyId())
                .orElseThrow(() -> new NotFoundException("Property not found"));

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
                .updatedAt(LocalDateTime.now())
                .images(locationCreateRequest.getImages())
                .build();

        return locationRepository.save(location);
    }

    @Override
    public Location getById(Integer locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new NotFoundException("Location not found"));
    }

    @Override
    public void delete(Integer locationId) {
        locationRepository.deleteById(locationId);
    }

    @Override
    public void deleteLocationEdit(Integer locationEditId) {
        locationEditRepository.deleteById(locationEditId);
    }

    @Override
    public Location updateStatus(Integer locationId, LocationStatusRequest locationStatusRequest) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new NotFoundException("Location not found"));
        location.setStatusEdit(locationStatusRequest.getStatusEdit());
        location.setLocationEdit(null);
        return locationRepository.save(location);
    }

    @Override
    @Transactional
    public LocationEdit update(Integer locationId, LocationEditRequest locationEditRequest) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new NotFoundException("Location not found"));

        Property property = propertyRepository.findById(locationEditRequest.getPropertyId())
                .orElseThrow(() -> new NotFoundException("Property not found"));
        AdvertiseForm advertiseForm = advertiseFormRepository.findById(locationEditRequest.getAdvertiseFormId())
                .orElseThrow(() -> new NotFoundException("Advertise form not found"));
        LocationType locationType = locationTypeRepository.findById(locationEditRequest.getLocationTypeId())
                .orElseThrow(() -> new NotFoundException("Location type not found"));
        User user = userRepository.findById(locationEditRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

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
                .orElseThrow(() -> new NotFoundException("Location not found"));

        Property property = propertyRepository.findById(locationEditByRootRequest.getPropertyId())
                .orElseThrow(() -> new NotFoundException("Property not found"));
        AdvertiseForm advertiseForm = advertiseFormRepository.findById(locationEditByRootRequest.getAdvertiseFormId())
                .orElseThrow(() -> new NotFoundException("Advertise form not found"));
        LocationType locationType = locationTypeRepository.findById(locationEditByRootRequest.getLocationTypeId())
                .orElseThrow(() -> new NotFoundException("Location type not found"));

        location.setPlanning(locationEditByRootRequest.getPlanning());
        location.setLatitude(locationEditByRootRequest.getLatitude());
        location.setLongitude(locationEditByRootRequest.getLongitude());
        location.setAddress(locationEditByRootRequest.getAddress());
        location.setUpdatedAt(LocalDateTime.now());
        location.setProperty(property);
        location.setStatusEdit(false);
        location.setLocationEdit(null);
        location.setAdsForm(advertiseForm);
        location.setLocationType(locationType);
        location.setImages(locationEditByRootRequest.getImageUrls());

        return locationRepository.save(location);
    }



    @Override
    public Page<Location> findAllLocationReview(Integer propertyId, Integer parentId, String search, PageRequestCustom pageRequestCustom) {
        return locationRepository.findAllLocationReview(propertyId, parentId, search, pageRequestCustom.pageRequest());
    }


    @Override
    public Location locationReview(Integer locationId, Boolean review) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new NotFoundException("Location not found"));
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

    @Override
    public Location getLocationById(Integer locationId) {
        return locationRepository.findById(locationId).orElse(null);
    }

    @Override
    public boolean checkExistAdvertises(Integer locationId) {
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(1, 10);
        Page<Advertise> advertisePage = advertiseRepository.findAllByLocationId(locationId, "", pageRequestCustom.pageRequest());
        if (advertisePage.getContent().isEmpty())
        {
            return false;
        }
        return true;
    }
}
