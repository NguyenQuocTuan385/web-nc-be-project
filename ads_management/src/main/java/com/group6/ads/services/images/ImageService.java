package com.group6.ads.services.images;

import com.group6.ads.repositories.database.images.ImageRepository;
import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.repositories.database.locations.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) { this.imageRepository = imageRepository; }
}
