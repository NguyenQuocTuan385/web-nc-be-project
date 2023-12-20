package com.group6.ads.controllers.advertises;

import com.group6.ads.controllers.advertises.models.AdvertiseRequest;
import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.services.advertises.AdvertiseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
    @Operation(summary = "get all advertise by location id")
    @GetMapping("locations/{locationId}/advertises")
    public ResponseEntity<List<Advertise>> findAllByLocationId(@PathVariable Integer locationId) {
        return ResponseEntity.ok(advertiseService.findAllByLocationId(locationId));
    }
    @Operation(summary = "cultural department create new advertise")
    @PostMapping("locations/{locationId}/advertises")
    public ResponseEntity<Advertise> create(@PathVariable Integer locationId, @RequestBody @Valid AdvertiseRequest advertiseRequest) {
        return ResponseEntity.ok(advertiseService.create(locationId, advertiseRequest));
    }

    @Operation(summary = "cultural department update advertise")
    @PutMapping("advertises/{advertiseId}")
    public ResponseEntity<Advertise> updateByRoot(@PathVariable Integer advertiseId, @RequestBody @Valid AdvertiseRequest advertiseRequest) {
        return ResponseEntity.ok(advertiseService.updateByRoot(advertiseId, advertiseRequest));
    }

    @Operation(summary = "cultural department delete advertise")
    @DeleteMapping("advertises/{advertiseId}")
    public ResponseEntity<Void> deleteByRoot(@PathVariable Integer advertiseId) {
        advertiseService.delete(advertiseId);
        return ResponseEntity.ok().build();
    }
}
