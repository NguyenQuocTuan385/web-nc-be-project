package com.group6.ads.controllers.location.types;

import com.group6.ads.controllers.location.types.models.LocationTypeRequest;
import com.group6.ads.repositories.database.location.types.LocationType;
import com.group6.ads.services.location.types.LocationTypeService;
import com.group6.ads.util.PageRequestCustom;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.group6.ads.controllers.location.types
 * Create by Dang Ngoc Tien
 * Date 12/19/2023 - 10:32 PM
 * Description: ...
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/location-types")
@Tag(name = "Location Type", description = "Location Type API")
public class LocationTypeController {
    @NonNull
    final LocationTypeService locationTypeService;
    @PostMapping
    public ResponseEntity<LocationType> create(@RequestBody @Valid LocationTypeRequest locationTypeRequest) {
        return ResponseEntity.ok(locationTypeService.create(locationTypeRequest));
    }
    @GetMapping
    public ResponseEntity<Page<LocationType>> findAll(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize) {
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
        return ResponseEntity.ok(locationTypeService.findAll(search, pageRequestCustom));
    }
    @PutMapping("{id}")
    public ResponseEntity<LocationType> update(@RequestParam Integer id, @RequestBody @Valid LocationTypeRequest locationTypeRequest) {
        return ResponseEntity.ok(locationTypeService.update(id, locationTypeRequest));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@RequestParam Integer id) {
        locationTypeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
