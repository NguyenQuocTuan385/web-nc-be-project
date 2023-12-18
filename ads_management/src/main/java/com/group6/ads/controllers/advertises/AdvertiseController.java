package com.group6.ads.controllers.advertises;

import com.group6.ads.controllers.advertises.models.AdvertiseRequest;
import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.services.advertises.AdvertiseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class AdvertiseController {
    @NonNull
    final AdvertiseService advertiseService;
    @GetMapping("locations/{locationId}/advertises")
    public ResponseEntity<List<Advertise>> findAllByLocationId(@PathVariable Integer locationId) {
        return ResponseEntity.ok(advertiseService.findAllByLocationId(locationId));
    }
    @PostMapping("locations/{locationId}/advertises")
    public ResponseEntity<Advertise> create(@PathVariable Integer locationId, @RequestBody @Valid AdvertiseRequest advertiseRequest) {
        return ResponseEntity.ok(advertiseService.create(locationId, advertiseRequest));
    }
    @PutMapping("locations/{locationId}/advertises/{advertiseId}")
    public ResponseEntity<Advertise> updateLocation(@PathVariable Integer advertiseId, @PathVariable Integer locationId) {
        return ResponseEntity.ok(advertiseService.updateLocation(advertiseId, locationId));
    }
}
