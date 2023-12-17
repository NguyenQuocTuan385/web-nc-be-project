package com.group6.ads.controllers.locations;

import com.group6.ads.controllers.locations.models.LocationDetails;
import com.group6.ads.dtos.ObjectResponseDTO;
import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.services.locations.LocationServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/locations")
public class LocationController {
    @NonNull
    LocationServiceImpl locationsService;

    @GetMapping("")
    public ResponseEntity<ObjectResponseDTO> getAllLocationsWithDetails() {
        return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponseDTO(HttpStatus.OK.value(), "Lấy danh sách locations thành công", locationsService.getAllLocationsWithDetails()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjectResponseDTO> getLocationsById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponseDTO(HttpStatus.OK.value(), "Lấy chi tiết locations id = " + id.toString() + "thành công", locationsService.getLocationById(id)));
    }
}

