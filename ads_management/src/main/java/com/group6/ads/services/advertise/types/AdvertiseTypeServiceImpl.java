package com.group6.ads.services.advertise.types;

import com.group6.ads.controllers.admin.advertise.types.models.AdvertiseTypeRequest;
import com.group6.ads.exceptions.NotFoundException;
import com.group6.ads.repositories.database.advertise.types.AdvertiseType;
import com.group6.ads.repositories.database.advertise.types.AdvertiseTypeRepository;
import com.group6.ads.util.PageRequestCustom;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * com.group6.ads.services.adversite.types
 * Create by Dang Ngoc Tien
 * Date 12/12/2023 - 11:33 PM
 * Description: ...
 */
@Service
@RequiredArgsConstructor
public class AdvertiseTypeServiceImpl implements AdvertiseTypeService {
    @NonNull
    final AdvertiseTypeRepository advertiseTypeRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Page<AdvertiseType> findAll(String search, PageRequestCustom pageRequestCustom) {
        try {
            logger.info("Find all advertise type");
            return advertiseTypeRepository.findAll(search, pageRequestCustom.pageRequest());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found advertise type");
        }
    }

    @Override
    public AdvertiseType createAdvertiseType(AdvertiseTypeRequest advertiseTypeRequest) {
        try {
            logger.info("Create new advertise type");
            AdvertiseType advertiseType = AdvertiseType.builder()
                    .name(advertiseTypeRequest.getName())
                    .description(advertiseTypeRequest.getDescription())
                    .createdAt(LocalDateTime.now())
                    .build();
            return advertiseTypeRepository.save(advertiseType);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found advertise type");
        }
    }

    @Override
    public AdvertiseType updateAdvertiseType(Integer id, AdvertiseTypeRequest advertiseTypeRequest) {
        try {
            logger.info("Update advertise type");
            AdvertiseType advertiseType = advertiseTypeRepository
                    .findById(id)
                    .orElseThrow(() -> new NotFoundException("Property not found"));

            advertiseType.setName(advertiseTypeRequest.getName());
            advertiseType.setDescription(advertiseTypeRequest.getDescription());
            return advertiseTypeRepository.save(advertiseType);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found advertise type");
        }
    }

    @Override
    public void deleteAdvertiseType(Integer id) {
        try {
            logger.info("Delete advertise type");
            AdvertiseType advertiseType = advertiseTypeRepository
                    .findById(id)
                    .orElseThrow(() -> new NotFoundException("Property not found"));
            advertiseTypeRepository.delete(advertiseType);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found advertise type");
        }
    }
}
