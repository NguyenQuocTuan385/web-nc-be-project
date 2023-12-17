package com.group6.ads.controllers.locations;

import com.group6.ads.controllers.locations.models.LocationCreateRequest;
import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.services.locations.LocationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/")
@Tag(name = "Location ", description = "Location  API")
public class LocationController {
    @NonNull
    final LocationService locationService;

    @PostMapping("locations")
    public ResponseEntity<Location> create(@RequestBody @Valid LocationCreateRequest locationCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.create(locationCreateRequest));
    }

    @GetMapping("locations")
    public ResponseEntity<List<Location>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(locationService.findAll());
    }

    @GetMapping("properties/{propertyId}/locations")
    public ResponseEntity<List<Location>> getAllByPropertyId(@PathVariable Integer propertyId) {
        return ResponseEntity.status(HttpStatus.OK).body(locationService.getAllByPropertyId(propertyId));
    }

    @DeleteMapping("locations/{locationId}")
    public ResponseEntity<Void> delete(@PathVariable Integer locationId) {
        locationService.delete(locationId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

