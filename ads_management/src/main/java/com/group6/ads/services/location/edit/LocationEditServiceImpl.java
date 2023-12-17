package com.group6.ads.services.location.edit;

import com.group6.ads.controllers.location.edit.LocationEditRequest;
import com.group6.ads.repositories.database.advertise.forms.AdvertiseForm;
import com.group6.ads.repositories.database.advertise.forms.AdvertiseFormRepository;
import com.group6.ads.repositories.database.location.edit.LocationEdit;
import com.group6.ads.repositories.database.location.edit.LocationEditRepository;
import com.group6.ads.repositories.database.location.types.LocationType;
import com.group6.ads.repositories.database.location.types.LocationTypeRepository;
import com.group6.ads.repositories.database.properties.Property;
import com.group6.ads.repositories.database.properties.PropertyRepository;
import com.group6.ads.repositories.database.users.User;
import com.group6.ads.repositories.database.users.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class LocationEditServiceImpl implements LocationEditService {
    private final LocationEditRepository locationEditRepository;
    private final PropertyRepository propertyRepository;
    private final AdvertiseFormRepository advertiseFormRepository;
    private final LocationTypeRepository locationTypeRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public LocationEdit createRequestEditLocation(LocationEditRequest locationEditRequest) {
        Property property = propertyRepository.findById(locationEditRequest.getPropertyId()).orElse(null);
        AdvertiseForm advertiseForm = advertiseFormRepository.findById(locationEditRequest.getAdsFormId()).orElse(null);
        LocationType locationType = locationTypeRepository.findById(locationEditRequest.getLocationTypeId()).orElse(null);
        User user = userRepository.findById(locationEditRequest.getUserId()).orElse(null);

        LocationEdit locationEdit = LocationEdit.builder()
                .address(locationEditRequest.getAddress())
                .content(locationEditRequest.getContent())
                .latitude(locationEditRequest.getLatitude())
                .longitude(locationEditRequest.getLongitude())
                .planning(locationEditRequest.getPlanning())
                .adsForm(advertiseForm)
                .locationType(locationType)
                .user(user)
                .property(property)
                .createdAt(LocalDateTime.now())
//                .images(locationEditRequest.getImages())
                .build();
        locationEditRepository.save(locationEdit);
        return locationEdit;
    }
}
