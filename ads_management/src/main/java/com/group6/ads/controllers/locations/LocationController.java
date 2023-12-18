package com.group6.ads.controllers.locations;

import com.group6.ads.controllers.locations.models.LocationEditByRootRequest;
import com.group6.ads.controllers.locations.models.LocationEditRequest;
import com.group6.ads.controllers.locations.models.LocationCreateRequest;
import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.repositories.database.locations.edit.LocationEdit;
import com.group6.ads.services.locations.LocationService;
import com.group6.ads.util.PageRequestCustom;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Page<Location>> getAll(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize) {
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(locationService.findAll(search, pageRequestCustom));
    }

    @GetMapping("properties/{propertyId}/locations")
    public ResponseEntity<Page<Location>> getAllByPropertyId(
            @PathVariable Integer propertyId,
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize) {
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(locationService.getAllByPropertyId(propertyId, search, pageRequestCustom));
    }

    @DeleteMapping("locations/{locationId}")
    public ResponseEntity<Void> delete(@PathVariable Integer locationId) {
        locationService.delete(locationId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/locations/{locationId}")
    ResponseEntity<LocationEdit> update(@PathVariable Integer locationId, @RequestBody LocationEditRequest locationEditRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(locationService.update(locationId, locationEditRequest));
    }

    @PutMapping("/locations/{locationId}")
    ResponseEntity<Location> updateByRoot(@PathVariable Integer locationId, @RequestBody LocationEditByRootRequest locationEditByRootRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(locationService.updateByRoot(locationId, locationEditByRootRequest));
    }

    @GetMapping("locations/review")
    public ResponseEntity<Page<Location>> findAllLocationReview(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize) {
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(locationService.findAllLocationReview(search, pageRequestCustom));
    }

    @PutMapping("/locations/{locationId}/review")
    ResponseEntity<Location> locationReview(@PathVariable Integer locationId, @RequestParam(required = false, value = "review", defaultValue = "true") Boolean review) {
        return ResponseEntity.status(HttpStatus.OK).body(locationService.locationReview(locationId, review));
    }

}

