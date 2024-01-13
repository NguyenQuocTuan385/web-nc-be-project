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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Page<Location> findAll(String search, PageRequestCustom pageRequestCustom) {
        try {
            logger.info("Find all location");
            return locationRepository.findAll(search, pageRequestCustom.pageRequest());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found location");
        }
    }

    @Override
    public Page<Location> findAll(Integer[] propertyId, Integer[] parentId, String search, PageRequestCustom pageRequestCustom) {
        try {
            logger.info("Find all location");
            if(propertyId.length == 0)
                propertyId = null;
            if(parentId.length == 0)
                parentId = null;

            return locationRepository.findAll(propertyId, parentId, search, pageRequestCustom.pageRequest());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found location");
        }

    }

    @Override
    public Page<Location> getAllByPropertyId(Integer propertyId, String search, PageRequestCustom pageRequestCustom) {
        try {
            logger.info("Find all location by property id");
            return locationRepository.findAllByPropertyId(propertyId, search, pageRequestCustom.pageRequest());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found location");
        }
    }

    public Location create(LocationCreateRequest locationCreateRequest) {
        try {
            logger.info("Create new location");
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
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found location");
        }
    }

    @Override
    public Location getById(Integer locationId) {
        try {
            logger.info("Get location by id");
            return locationRepository.findById(locationId)
                    .orElseThrow(() -> new NotFoundException("Location not found"));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found location");
        }
    }

    @Override
    public void delete(Integer locationId) {
        try {
            logger.info("Delete location");
            locationRepository.deleteById(locationId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found location");
        }
    }

    @Override
    public void deleteLocationEdit(Integer locationEditId) {
        try {
            logger.info("Delete location edit");
            locationEditRepository.deleteById(locationEditId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found location edit");
        }
    }

    @Override
    public Location updateStatus(Integer locationId, LocationStatusRequest locationStatusRequest) {
        try {
            logger.info("Update status location");
            Location location = locationRepository.findById(locationId)
                    .orElseThrow(() -> new NotFoundException("Location not found"));
            location.setStatusEdit(locationStatusRequest.getStatusEdit());
            location.setLocationEdit(null);
            return locationRepository.save(location);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found location");
        }
    }

    @Override
    @Transactional
    public LocationEdit update(Integer locationId, LocationEditRequest locationEditRequest) {
        try {
            logger.info("Update location");
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
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found location");
        }
    }

    @Override
    public Location updateByRoot(Integer locationId, LocationEditByRootRequest locationEditByRootRequest) {
        try {
            logger.info("Update location by root");
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
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found location");
        }
    }



    @Override
    public Page<Location> findAllLocationReview(Integer propertyId, Integer parentId, String search, PageRequestCustom pageRequestCustom) {
        try {
            logger.info("Find all location review");
            return locationRepository.findAllLocationReview(propertyId, parentId, search, pageRequestCustom.pageRequest());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found location");
        }
    }


    @Override
    public Location locationReview(Integer locationId, Boolean review) {
        try {
            logger.info("Location review");
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
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found location");
        }
    }

    @Override
    public Location getLocationById(Integer locationId) {
        try {
            logger.info("Get location by id");
            return locationRepository.findById(locationId).orElse(null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found location");
        }
    }

    @Override
    public boolean checkExistAdvertises(Integer locationId) {
        try {
            logger.info("Check exist advertises");
            PageRequestCustom pageRequestCustom = PageRequestCustom.of(1, 10);
            Page<Advertise> advertisePage = advertiseRepository.findAllByLocationId(locationId, "", pageRequestCustom.pageRequest());
            if (advertisePage.getContent().isEmpty())
            {
                return false;
            }
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found location");
        }
    }
}
