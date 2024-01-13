package com.group6.ads.controllers.admin.advertise.types;

import com.group6.ads.controllers.admin.advertise.types.models.AdvertiseTypeRequest;
import com.group6.ads.repositories.database.advertise.types.AdvertiseType;
import com.group6.ads.services.advertise.types.AdvertiseTypeService;
import com.group6.ads.util.PageRequestCustom;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * com.group6.ads.controllers.advetise.types
 * Create by Dang Ngoc Tien
 * Date 12/12/2023 - 11:27 PM
 * Description: ...
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/advertise-types")
@CrossOrigin("*")
public class AdvertiseTypesController {
    @NonNull
    final AdvertiseTypeService advertiseTypeService;

    @GetMapping
    ResponseEntity<?> findAl(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize) {
        try {
            PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
            return ResponseEntity.ok(advertiseTypeService.findAll(search, pageRequestCustom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    ResponseEntity<?> createAdvertiseType(@RequestBody @Valid AdvertiseTypeRequest advertiseTypeRequest) {
        try {
            return ResponseEntity.ok(advertiseTypeService.createAdvertiseType(advertiseTypeRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    ResponseEntity<?> updateAdvertiseType(@PathVariable Integer id, @RequestBody @Valid AdvertiseTypeRequest advertiseTypeRequest) {
        try {
            return ResponseEntity.ok(advertiseTypeService.updateAdvertiseType(id, advertiseTypeRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    ResponseEntity<?> deleteAdvertiseType(@PathVariable Integer id) {
        try {
            advertiseTypeService.deleteAdvertiseType(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
