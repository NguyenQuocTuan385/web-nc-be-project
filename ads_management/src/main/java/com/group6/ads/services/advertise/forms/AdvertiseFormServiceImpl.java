package com.group6.ads.services.advertise.forms;

import com.group6.ads.controllers.advertise.forms.models.AdvertiseFormRequest;
import com.group6.ads.repositories.database.advertise.forms.AdvertiseForm;
import com.group6.ads.repositories.database.advertise.forms.AdvertiseFormRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
    public List<AdvertiseForm> findAll() {
        return advertiseFormRepository.findAll();
    }

    @Override
    public AdvertiseForm create(AdvertiseFormRequest advertiseFormRequest) {
        AdvertiseForm advertiseForm = new AdvertiseForm();
        advertiseForm.setName(advertiseFormRequest.getName());
        advertiseForm.setDescription(advertiseFormRequest.getDescription());
        advertiseForm.setCreatedAt(LocalDateTime.now());
        return advertiseFormRepository.save(advertiseForm);
    }

    @Override
    public AdvertiseForm update(Integer id, AdvertiseFormRequest advertiseFormRequest) {
        AdvertiseForm advertiseForm = advertiseFormRepository
                .findById(id)
                .orElseThrow( () -> new RuntimeException("Not found advertise form"));
        advertiseForm.setName(advertiseFormRequest.getName());
        advertiseForm.setDescription(advertiseFormRequest.getDescription());
        return advertiseFormRepository.save(advertiseForm);
    }

    @Override
    public void delete(Integer id) {
        AdvertiseForm advertiseForm = advertiseFormRepository
                .findById(id)
                .orElseThrow( () -> new RuntimeException("Not found advertise form"));
        advertiseFormRepository.delete(advertiseForm);
    }
}
