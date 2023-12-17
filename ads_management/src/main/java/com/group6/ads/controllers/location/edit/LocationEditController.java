package com.group6.ads.controllers.location.edit;

import com.group6.ads.dtos.ObjectResponseDTO;
import com.group6.ads.repositories.database.location.edit.LocationEdit;
import com.group6.ads.services.location.edit.LocationEditServiceImpl;
import jdk.jshell.Snippet;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/locations-edit")
public class LocationEditController {
    @NonNull LocationEditServiceImpl locationEditService;

    @PostMapping("")
    public ResponseEntity<ObjectResponseDTO> createRequestEditLocation(@RequestBody LocationEditRequest locationEditRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ObjectResponseDTO(HttpStatus.OK.value(), "Gửi yêu cầu chỉnh sửa thành công", locationEditService.createRequestEditLocation(locationEditRequest)));
    }
}
