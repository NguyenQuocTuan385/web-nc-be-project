package com.group6.ads.controllers.advertises;

import com.group6.ads.controllers.advertises.models.AdvertiseEditByRootRequest;
import com.group6.ads.controllers.advertises.models.AdvertiseEditRequest;
import com.group6.ads.controllers.advertises.models.AdvertiseRequest;
import com.group6.ads.controllers.locations.models.LocationEditRequest;
import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.repositories.database.advertises.edit.AdvertiseEdit;
import com.group6.ads.repositories.database.locations.edit.LocationEdit;
import com.group6.ads.repositories.database.properties.Property;
import com.group6.ads.services.advertises.AdvertiseService;
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

import java.util.List;

/**
 * com.group6.ads.controllers.advertise
 * Create by Dang Ngoc Tien
 * Date 12/18/2023 - 11:19 PM
 * Description: ...
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/")
@Tag(name = "Advertise ", description = "Advertise API")
@CrossOrigin("*")
public class AdvertiseController {
    @NonNull
    final AdvertiseService advertiseService;

    @Operation(summary = "get all advertises")
    @GetMapping("advertises")
    public ResponseEntity<Page<Advertise>> findAll(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize) {
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
        return ResponseEntity.ok(advertiseService.findAll(search, pageRequestCustom));
    }

    @Operation(summary = "get advertise by id")
    @GetMapping("advertises/{id}")
    public ResponseEntity<Advertise> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(advertiseService.findById(id));
    }

    @Operation(summary = "get all advertise by location id")
    @GetMapping("locations/{locationId}/advertises")
    public ResponseEntity<Page<Advertise>> findAllByLocationId(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize,
            @PathVariable Integer locationId) {
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
        return ResponseEntity.ok(advertiseService.findAllByLocationId(locationId, search, pageRequestCustom));
    }
    @Operation(summary = "cultural department create new advertise")
    @PostMapping("locations/{locationId}/advertises")
    public ResponseEntity<Advertise> create(@PathVariable Integer locationId, @RequestBody @Valid AdvertiseRequest advertiseRequest) {
        return ResponseEntity.ok(advertiseService.create(locationId, advertiseRequest));
    }

    @Operation(summary = "cultural department update advertise")
    @PutMapping("advertises/{advertiseId}")
    public ResponseEntity<Advertise> updateByRoot(@PathVariable Integer advertiseId, @RequestBody @Valid AdvertiseEditByRootRequest advertiseEditByRootRequest) {
        return ResponseEntity.ok(advertiseService.updateByRoot(advertiseId, advertiseEditByRootRequest));
    }

    @Operation(summary = "cultural department delete advertise")
    @DeleteMapping("advertises/{advertiseId}")
    public ResponseEntity<Void> deleteByRoot(@PathVariable Integer advertiseId) {
        advertiseService.delete(advertiseId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "officer update advertise by advertise id")
    @PostMapping("/advertises/{advertiseId}")
    ResponseEntity<AdvertiseEdit> update(@PathVariable Integer advertiseId, @RequestBody AdvertiseEditRequest advertiseEditRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(advertiseService.update(advertiseId, advertiseEditRequest));
    }
}
