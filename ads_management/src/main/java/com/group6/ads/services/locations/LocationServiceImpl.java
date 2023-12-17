package com.group6.ads.services.locations;

import com.group6.ads.controllers.locations.models.LocationDetails;
import com.group6.ads.repositories.database.images.Image;
import com.group6.ads.repositories.database.images.ImageRepository;
import com.group6.ads.repositories.database.images.ImageShort;
import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.repositories.database.locations.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService{
    private final LocationRepository locationsRepository;
    private final ImageRepository imageRepository;

    @Override
    public List<Location> getAllLocationsWithDetails() {
        return locationsRepository.findAllWithDetails();
    }

    @Override
    public Optional<LocationDetails> getLocationById(Integer locationId) {
        Optional<Location> location = locationsRepository.findById(locationId);
        List<Image> imageList = imageRepository.findImageByLocationId(locationId);

        List<ImageShort> newList = new ArrayList<>();

        try {
            if (location.isPresent() && !imageList.isEmpty()) {
                imageList.forEach(image -> {
                    ImageShort imageShortItem = new ImageShort(image.getId(), image.getImgUrl());
                    newList.add(imageShortItem);
                });
                LocationDetails locationDetails = new LocationDetails(location, newList);
                return Optional.ofNullable(locationDetails);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }

        return Optional.empty();
    }

}
