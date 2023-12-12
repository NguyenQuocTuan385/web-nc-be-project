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
@RequestMapping("${api.prefix}/districts")
public class PropertyController {
    @NonNull
    final PropertyService propertyService;

    @GetMapping
    ResponseEntity<List<Property>> findAllDistrict() {
        return ResponseEntity.status(HttpStatus.OK).body(propertyService.findAllByPropertyParentId(1));
    }
    @GetMapping("{propertyParentId}")
    ResponseEntity<List<Property>> findAllByPropertyParentId(@PathVariable Integer propertyParentId) {
        return ResponseEntity.status(HttpStatus.OK).body(propertyService.findAllByPropertyParentId(propertyParentId));
    }

    @PostMapping
    ResponseEntity<Property> save(@RequestBody @Valid PropertyRequest properties) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.save(properties));
    }
    @PutMapping("{id}")
    ResponseEntity<Property> update(@PathVariable Integer id, @RequestBody @Valid PropertyRequest propertyRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(propertyService.update(id, propertyRequest));
    }
    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable Integer id) {
        propertyService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
