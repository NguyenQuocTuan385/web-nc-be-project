package com.group6.ads.controllers.admin.location.types;

import com.group6.ads.controllers.admin.location.types.models.LocationTypeRequest;
import com.group6.ads.repositories.database.location.types.LocationType;
import com.group6.ads.services.location.types.LocationTypeService;
import com.group6.ads.util.PageRequestCustom;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * com.group6.ads.controllers.location.types
 * Create by Dang Ngoc Tien
 * Date 12/19/2023 - 10:32 PM
 * Description: ...
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/location-types")
@CrossOrigin("*")
@Tag(name = "Location Type", description = "Location Type API")
public class LocationTypeController {
    @NonNull
    final LocationTypeService locationTypeService;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid LocationTypeRequest locationTypeRequest) {
        try {
            return ResponseEntity.ok(locationTypeService.create(locationTypeRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize) {
        try {
            PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
            return ResponseEntity.ok(locationTypeService.findAll(search, pageRequestCustom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestParam Integer id, @RequestBody @Valid LocationTypeRequest locationTypeRequest) {
        try {
            return ResponseEntity.ok(locationTypeService.update(id, locationTypeRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@RequestParam Integer id) {
        try {
            locationTypeService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
