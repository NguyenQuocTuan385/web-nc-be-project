package com.group6.ads.controllers.advertise.types;

import com.group6.ads.controllers.advertise.types.models.AdvertiseTypeRequest;
import com.group6.ads.repositories.database.advertise.types.AdvertiseType;
import com.group6.ads.services.advertise.types.AdvertiseTypeService;
import com.group6.ads.util.PageRequestCustom;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.group6.ads.controllers.advetise.types
 * Create by Dang Ngoc Tien
 * Date 12/12/2023 - 11:27 PM
 * Description: ...
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/advertise-types")
public class AdvertiseTypesController {
    @NonNull
    final AdvertiseTypeService advertiseTypeService;

    @GetMapping
    ResponseEntity<Page<AdvertiseType>> findAl(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize) {
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
        return ResponseEntity.ok(advertiseTypeService.findAll(search, pageRequestCustom));
    }

    @PostMapping
    ResponseEntity<AdvertiseType> createAdvertiseType(@RequestBody @Valid AdvertiseTypeRequest advertiseTypeRequest) {
        return ResponseEntity.ok(advertiseTypeService.createAdvertiseType(advertiseTypeRequest));
    }

    @PutMapping("{id}")
    ResponseEntity<AdvertiseType> updateAdvertiseType(@PathVariable Integer id, @RequestBody @Valid AdvertiseTypeRequest advertiseTypeRequest) {
        return ResponseEntity.ok(advertiseTypeService.updateAdvertiseType(id, advertiseTypeRequest));
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteAdvertiseType(@PathVariable Integer id) {
        advertiseTypeService.deleteAdvertiseType(id);
        return ResponseEntity.ok().build();
    }
}
