package com.group6.ads.controllers.location.edit;

import com.group6.ads.controllers.location.edit.models.LocationEditRequest;
import com.group6.ads.repositories.database.location.edit.LocationEdit;
import com.group6.ads.services.location.edit.LocationEditService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/locations")
@Tag(name = "Location ", description = "Location API")
public class LocationEditController {
    @NonNull
    final LocationEditService locationEditService;

    @PutMapping("{locationId}")
    ResponseEntity<LocationEdit> update(@PathVariable Integer locationId, @RequestBody LocationEditRequest locationEditRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(locationEditService.update(locationId, locationEditRequest));
    }
}
