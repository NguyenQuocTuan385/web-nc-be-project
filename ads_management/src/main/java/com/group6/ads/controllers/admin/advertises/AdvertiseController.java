package com.group6.ads.controllers.admin.advertises;

import com.group6.ads.controllers.admin.advertises.models.AdvertiseEditByRootRequest;
import com.group6.ads.controllers.admin.advertises.models.AdvertiseEditRequest;
import com.group6.ads.controllers.admin.advertises.models.AdvertiseLicensingRequest;
import com.group6.ads.controllers.admin.advertises.models.AdvertiseRequest;
import com.group6.ads.controllers.admin.advertises.models.AdvertiseStatusRequest;
import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.repositories.database.advertises.edit.AdvertiseEdit;
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

    @Operation(summary = "get advertise by id")
    @GetMapping("advertises/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(advertiseService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "get all advertise by location id")
    @GetMapping("locations/{locationId}/advertises")
    public ResponseEntity<?> findAllByLocationId(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1) Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10") Integer pageSize,
            @PathVariable Integer locationId) {
        try {
            PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
            return ResponseEntity.ok(advertiseService.findAllByLocationId(locationId, search, pageRequestCustom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "cultural department create new advertise")
    @PostMapping("locations/{locationId}/advertises")
    public ResponseEntity<?> create(@PathVariable Integer locationId,
            @RequestBody @Valid AdvertiseRequest advertiseRequest) {
        try {
            return ResponseEntity.ok(advertiseService.create(locationId, advertiseRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "cultural department update advertise")
    @PutMapping("advertises/{advertiseId}")
    public ResponseEntity<?> updateByDCMS(@PathVariable Integer advertiseId,
            @RequestBody @Valid AdvertiseEditByRootRequest advertiseEditByRootRequest) {
        try {
            return ResponseEntity.ok(advertiseService.updateByDCMS(advertiseId, advertiseEditByRootRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "cultural department update advertise license")
    @PutMapping("advertises/{advertiseId}/license")
    public ResponseEntity<?> updateLicense(@PathVariable Integer advertiseId,
            @RequestBody @Valid AdvertiseLicensingRequest advertiseRequest) {
        try {
            return ResponseEntity.ok(advertiseService.updateLicense(advertiseId, advertiseRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @GetMapping("advertises")
    public ResponseEntity<?> findAllUnLicensingAdvertisements(
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
            return ResponseEntity.ok(advertiseService.findAllUnLicensingAdvertisements(propertyId,parentId,search, pageRequestCustom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "cultural department delete advertise")
    @DeleteMapping("advertises/{advertiseId}")
    public ResponseEntity<?> deleteByRoot(@PathVariable Integer advertiseId) {
        try {
            advertiseService.delete(advertiseId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @Operation(summary = "cultural department delete advertise edit")
    @DeleteMapping("advertises/edit/{advertiseId}")
    public ResponseEntity<?> deleteAdvertiseEdit(@PathVariable Integer advertiseId) {
        try {
            advertiseService.deleteAdvertiseEdit(advertiseId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "cultural department update advertise status")
    @PutMapping("advertises/{advertiseId}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Integer advertiseId, @RequestBody @Valid AdvertiseStatusRequest advertiseStatusRequest) {
        try {
            return ResponseEntity.ok(advertiseService.updateStatus(advertiseId, advertiseStatusRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "officer update advertise by advertise id")
    @PostMapping("/advertises/{advertiseId}")
    ResponseEntity<?> update(@PathVariable Integer advertiseId,
            @RequestBody AdvertiseEditRequest advertiseEditRequest) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(advertiseService.update(advertiseId, advertiseEditRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
