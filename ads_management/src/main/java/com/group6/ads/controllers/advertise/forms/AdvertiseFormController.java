package com.group6.ads.controllers.advertise.forms;

import com.group6.ads.controllers.advertise.forms.models.AdvertiseFormRequest;
import com.group6.ads.repositories.database.advertise.forms.AdvertiseForm;
import com.group6.ads.services.advertise.forms.AdvertiseFormService;
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
 * com.group6.ads.controllers.advertise.forms
 * Create by Dang Ngoc Tien
 * Date 12/17/2023 - 9:16 AM
 * Description: ...
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/advertise-forms")
public class AdvertiseFormController {
    @NonNull
    final AdvertiseFormService advertiseFormService;

    @GetMapping
    ResponseEntity<Page<AdvertiseForm>> findAll(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize) {
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
        return ResponseEntity.ok().body(advertiseFormService.findAll(search, pageRequestCustom));
    }

    @PostMapping
    ResponseEntity<AdvertiseForm> create(@RequestBody @Valid AdvertiseFormRequest advertiseFormRequest) {
        return ResponseEntity.ok().body(advertiseFormService.create(advertiseFormRequest));
    }

    @PutMapping("{id}")
    ResponseEntity<AdvertiseForm> update(@PathVariable Integer id, @RequestBody @Valid AdvertiseFormRequest advertiseFormRequest) {
        return ResponseEntity.ok().body(advertiseFormService.update(id, advertiseFormRequest));
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable Integer id) {
        advertiseFormService.delete(id);
        return ResponseEntity.ok().build();
    }
}
