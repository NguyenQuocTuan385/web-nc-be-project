package com.group6.ads.services.advertise.types;

import com.group6.ads.controllers.advertise.types.models.AdvertiseTypeRequest;
import com.group6.ads.repositories.database.advertise.types.AdvertiseType;
import com.group6.ads.repositories.database.advertise.types.AdvertiseTypeRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.group6.ads.services.adversite.types
 * Create by Dang Ngoc Tien
 * Date 12/12/2023 - 11:33 PM
 * Description: ...
 */
@Service
@RequiredArgsConstructor
public class AdvertiseTypeServiceImpl implements AdvertiseTypeService{
    @NonNull
    final AdvertiseTypeRepository advertiseTypeRepository;

    @Override
    public List<AdvertiseType> findAll() {
        return advertiseTypeRepository.findAll();
    }

    @Override
    public AdvertiseType createAdvertiseType(AdvertiseTypeRequest advertiseTypeRequest) {
        return advertiseTypeRepository.save(AdvertiseType.builder()
                .name(advertiseTypeRequest.getName())
                .description(advertiseTypeRequest.getDescription())
                .build());
    }

}
