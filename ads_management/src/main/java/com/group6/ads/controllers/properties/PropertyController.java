package com.group6.ads.controllers.properties;

import com.group6.ads.controllers.properties.models.PropertyRequest;
import com.group6.ads.repositories.database.properties.Property;
import com.group6.ads.services.properties.PropertyService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/properties")
public class PropertyController {
    @NonNull
    final PropertyService propertyService;

    @GetMapping("")
    ResponseEntity<List<Property>> getAllRoles() {
        return ResponseEntity.status(HttpStatus.OK).body(propertyService.findAll());
    }

    @PostMapping("")
    ResponseEntity<Property> createRoles(@RequestBody @Valid PropertyRequest properties) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.createProperties(properties));
    }

}
