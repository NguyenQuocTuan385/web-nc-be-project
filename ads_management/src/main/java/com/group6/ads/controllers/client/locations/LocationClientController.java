package com.group6.ads.controllers.client.locations;

import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.services.locations.LocationService;
import com.group6.ads.util.PageRequestCustom;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Location Client", description = "Location Client API")
@CrossOrigin("*")
public class LocationClientController {
    @NonNull
    final LocationService locationService;

    @Operation(summary = "cultural department get all location")
    @GetMapping("locations-client")
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "check exists advertises in location")
    @GetMapping("locations-client/{locationId}/exists-advertises")
    public ResponseEntity<?> checkExistsAdvertises(
            @PathVariable Integer locationId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(locationService.checkExistAdvertises(locationId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

