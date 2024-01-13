package com.group6.ads.controllers.admin.advertise.forms;

import com.group6.ads.controllers.admin.advertise.forms.models.AdvertiseFormRequest;
import com.group6.ads.repositories.database.advertise.forms.AdvertiseForm;
import com.group6.ads.services.advertise.forms.AdvertiseFormService;
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
 * com.group6.ads.controllers.advertise.forms
 * Create by Dang Ngoc Tien
 * Date 12/17/2023 - 9:16 AM
 * Description: ...
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/advertise-forms")
@CrossOrigin("*")
public class AdvertiseFormController {
    @NonNull
    final AdvertiseFormService advertiseFormService;

    @GetMapping
    ResponseEntity<?> findAll(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize) {
        try {
            PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
            return ResponseEntity.ok().body(advertiseFormService.findAll(search, pageRequestCustom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    ResponseEntity<?> create(@RequestBody @Valid AdvertiseFormRequest advertiseFormRequest) {
        try {
            return ResponseEntity.ok().body(advertiseFormService.create(advertiseFormRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid AdvertiseFormRequest advertiseFormRequest) {
        try {
            return ResponseEntity.ok().body(advertiseFormService.update(id, advertiseFormRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            advertiseFormService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
