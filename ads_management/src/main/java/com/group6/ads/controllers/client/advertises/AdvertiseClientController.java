package com.group6.ads.controllers.client.advertises;

import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.services.advertises.AdvertiseService;
import com.group6.ads.util.PageRequestCustom;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * com.group6.ads.controllers.advertise
 * Create by Dang Ngoc Tien
 * Date 12/18/2023 - 11:19 PM
 * Description: ...
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/")
@Tag(name = "Advertise Client", description = "Advertise Client API")
@CrossOrigin("*")
public class AdvertiseClientController {
    @NonNull
    final AdvertiseService advertiseService;

    @Operation(summary = "get all advertise by location id")
    @GetMapping("locations-client/{locationId}/advertises")
    public ResponseEntity<Page<Advertise>> findAllByLocationId(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1) Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10") Integer pageSize,
            @PathVariable Integer locationId) {
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
        return ResponseEntity.ok(advertiseService.findAllByLocationId(locationId, search, pageRequestCustom));
    }
}
