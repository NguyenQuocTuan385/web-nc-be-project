package com.group6.ads.services.advertise.forms;

import com.group6.ads.controllers.admin.advertise.forms.models.AdvertiseFormRequest;
import com.group6.ads.exceptions.NotFoundException;
import com.group6.ads.repositories.database.advertise.forms.AdvertiseForm;
import com.group6.ads.repositories.database.advertise.forms.AdvertiseFormRepository;
import com.group6.ads.util.PageRequestCustom;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * com.group6.ads.services.advertise.forms
 * Create by Dang Ngoc Tien
 * Date 12/17/2023 - 9:18 AM
 * Description: ...
 */
@Service
@RequiredArgsConstructor
public class AdvertiseFormServiceImpl implements AdvertiseFormService {
    @NonNull
    final AdvertiseFormRepository advertiseFormRepository;

    @Override
    public Page<AdvertiseForm> findAll(String search, PageRequestCustom pageRequestCustom) {
        return advertiseFormRepository.findAll(search, pageRequestCustom.pageRequest());
    }

    @Override
    public AdvertiseForm create(AdvertiseFormRequest advertiseFormRequest) {
        AdvertiseForm advertiseForm = AdvertiseForm.builder()
                .name(advertiseFormRequest.getName())
                .description(advertiseFormRequest.getDescription())
                .createdAt(LocalDateTime.now())
                .build();
        return advertiseFormRepository.save(advertiseForm);
    }

    @Override
    public AdvertiseForm update(Integer id, AdvertiseFormRequest advertiseFormRequest) {
        AdvertiseForm advertiseForm = advertiseFormRepository
                .findById(id)
                .orElseThrow( () -> new NotFoundException("Not found advertise form"));
        advertiseForm.setName(advertiseFormRequest.getName());
        advertiseForm.setDescription(advertiseFormRequest.getDescription());
        return advertiseFormRepository.save(advertiseForm);
    }

    @Override
    public void delete(Integer id) {
        AdvertiseForm advertiseForm = advertiseFormRepository
                .findById(id)
                .orElseThrow( () -> new NotFoundException("Not found advertise form"));
        advertiseFormRepository.delete(advertiseForm);
    }
}
