package com.group6.ads.controllers.properties;

import com.group6.ads.controllers.properties.models.PropertyRequest;
import com.group6.ads.repositories.database.properties.Property;
import com.group6.ads.services.properties.PropertyService;
import com.group6.ads.util.PageRequestCustom;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/properties")
public class PropertyController {
    @NonNull
    final PropertyService propertyService;
    @Operation(summary = "Find all districts")
    @GetMapping
    ResponseEntity<Page<Property>> findAllDistrict(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize) {
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(propertyService.findAllDistrict(search, pageRequestCustom));
    }

    @Operation(summary = "Find all ward by district id")
    @GetMapping("{propertyParentId}")
    ResponseEntity<Page<Property>> findAllByPropertyParentId(
            @PathVariable
            Integer propertyParentId,
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize) {
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(propertyService.findAllByPropertyParentId(propertyParentId, search, pageRequestCustom));
    }

    @Operation(summary = "cultural department create new district")
    @PostMapping
    ResponseEntity<Property> save(@RequestBody @Valid PropertyRequest properties) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.save(properties));
    }

    @Operation(summary = "cultural department update district")
    @PutMapping("{id}")
    ResponseEntity<Property> update(@PathVariable Integer id, @RequestBody @Valid PropertyRequest propertyRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(propertyService.update(id, propertyRequest));
    }

    @Operation(summary = "cultural department delete district or ward")
    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable Integer id) {
        propertyService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
