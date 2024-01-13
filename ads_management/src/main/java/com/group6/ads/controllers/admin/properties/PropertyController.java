package com.group6.ads.controllers.admin.properties;

import com.group6.ads.controllers.admin.properties.models.PropertyRequest;
import com.group6.ads.controllers.admin.properties.models.PropertyUpdateRequest;
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
@CrossOrigin("*")
public class PropertyController {
    @NonNull
    final PropertyService propertyService;
    @Operation(summary = "Find all districts")
    @GetMapping
    ResponseEntity<?> findAllDistrict(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize) {
        try {
            PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(propertyService.findAllDistrict(search, pageRequestCustom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Find all ward by district id")
    @GetMapping("{propertyParentId}")
    ResponseEntity<?> findAllByPropertyParentId(
            @PathVariable
            Integer propertyParentId,
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize) {
        try {
            PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(propertyService.findAllByPropertyParentId(propertyParentId, search, pageRequestCustom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "cultural department create new district")
    @PostMapping
    ResponseEntity<?> save(@RequestBody @Valid PropertyRequest properties) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.save(properties));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "cultural department update district")
    @PutMapping("{id}")
    ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid PropertyUpdateRequest propertyRequest) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(propertyService.update(id, propertyRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "cultural department delete district or ward")
    @DeleteMapping("{id}")
    ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            propertyService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
