package com.group6.ads.controllers.admin.locations;

import com.group6.ads.controllers.admin.locations.models.LocationEditRequest;
import com.group6.ads.controllers.admin.locations.models.LocationStatusRequest;
import com.group6.ads.controllers.admin.locations.models.LocationEditByRootRequest;
import com.group6.ads.controllers.admin.locations.models.LocationCreateRequest;
import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.repositories.database.locations.edit.LocationEdit;
import com.group6.ads.services.locations.LocationService;
import com.group6.ads.util.PageRequestCustom;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/")
@Tag(name = "Location ", description = "Location  API")
@CrossOrigin("*")
public class LocationController {
    @NonNull
    final LocationService locationService;

    @Operation(summary = "cultural department create new location")
    @PostMapping("locations")
    public ResponseEntity<?> create(@RequestBody @Valid LocationCreateRequest locationCreateRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(locationService.create(locationCreateRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "cultural department get all location")
    @GetMapping("locations")
    public ResponseEntity<?> getAll(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize) {
        try {
            PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(locationService.findAll(search, pageRequestCustom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "check exists advertises in location")
    @GetMapping("locations/{locationId}/exists-advertises")
    public ResponseEntity<?> checkExistsAdvertises(
            @PathVariable Integer locationId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(locationService.checkExistAdvertises(locationId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "cultural department get location with property id and parent id")
    @GetMapping("properties/locations")
    public ResponseEntity<?> getAllWithPropertyAndParent(
            @RequestParam(required = false, value = "propertyId[]", defaultValue = "")
            Integer[] propertyId,
            @RequestParam(required = false, value = "parentId[]", defaultValue = "")
            Integer[] parentId,
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize) {
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(locationService.findAll(propertyId, parentId, search, pageRequestCustom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "cultural department get location by ward id")
    @GetMapping("properties/{propertyId}/locations")
    public ResponseEntity<?> getAllByPropertyId(
            @PathVariable Integer propertyId,
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize) {
        try {
            PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(locationService.getAllByPropertyId(propertyId, search, pageRequestCustom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "cultural department delete location by location id")
    @DeleteMapping("locations/{locationId}")
    public ResponseEntity<?> delete(@PathVariable Integer locationId) {
        try {
            locationService.delete(locationId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "officer update location by location id")
    @PostMapping("/locations/{locationId}")
    ResponseEntity<?> update(@PathVariable Integer locationId, @RequestBody LocationEditRequest locationEditRequest) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(locationService.update(locationId, locationEditRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "cultural department update location by location id")
    @PutMapping("/locations/{locationId}")
    ResponseEntity<?> updateByRoot(@PathVariable Integer locationId, @RequestBody LocationEditByRootRequest locationEditByRootRequest) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(locationService.updateByRoot(locationId, locationEditByRootRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "cultural department get location review")
    @GetMapping("locations/review")
    public ResponseEntity<?> findAllLocationReview(
            @RequestParam(required = false, value = "propertyId", defaultValue = "")
            Integer propertyId,
            @RequestParam(required = false, value = "parentId", defaultValue = "")
            Integer parentId,
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize) {
        try {
            PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(locationService.findAllLocationReview(propertyId,parentId,search, pageRequestCustom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "cultural department review location by location id")
    @PutMapping("/locations/{locationId}/review")
    ResponseEntity<?> locationReview(@PathVariable Integer locationId, @RequestParam(required = false, value = "review", defaultValue = "true") Boolean review) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(locationService.locationReview(locationId, review));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @Operation(summary = "cultural department update status location by location id")
    @PutMapping("/locations/{locationId}/status")
    ResponseEntity<?> updateStatus(@PathVariable Integer locationId, @RequestBody LocationStatusRequest locationStatusRequest) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(locationService.updateStatus(locationId, locationStatusRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @Operation(summary = "cultural department delete location edit by location edit id")
    @DeleteMapping("locations/edit/{locationEditId}")
    public ResponseEntity<?> deleteLocationEdit(@PathVariable Integer locationEditId) {
        try {
            locationService.deleteLocationEdit(locationEditId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "officer get location by id")
    @GetMapping("locations/{locationId}")
    ResponseEntity<?> getLocationById(@PathVariable Integer locationId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(locationService.getLocationById(locationId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

