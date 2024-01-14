package com.group6.ads.controllers.client.properties;

import com.group6.ads.services.properties.PropertyService;
import com.group6.ads.util.PageRequestCustom;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/properties-client")
@CrossOrigin("*")
@Tag(name = "Property Client", description = "Property Client API")
public class PropertyClientController {
    @NonNull
    final PropertyService propertyService;

    @Operation(summary = "Find Property By Ward District Address")
    @GetMapping("/findByWardAndDistrict")
    ResponseEntity<?> findPropertyByWardDistrictAddress(
            @RequestParam(required = false, value = "ward")
            String ward,
            @RequestParam(required = false, value = "district")
            String district) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(propertyService.findPropertyByWardDistrictAddress(ward, district));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
