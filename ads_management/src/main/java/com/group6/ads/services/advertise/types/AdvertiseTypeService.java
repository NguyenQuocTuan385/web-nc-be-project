package com.group6.ads.services.advertise.types;

import com.group6.ads.controllers.advertise.types.models.AdvertiseTypeRequest;
import com.group6.ads.repositories.database.advertise.types.AdvertiseType;
import com.group6.ads.util.PageRequestCustom;
import org.springframework.data.domain.Page;

/**
 * com.group6.ads.services.adversite.types
 * Create by Dang Ngoc Tien
 * Date 12/12/2023 - 11:33 PM
 * Description: ...
 */
public interface AdvertiseTypeService {
    Page<AdvertiseType> findAll(String search, PageRequestCustom pageRequestCustom);

    AdvertiseType createAdvertiseType(AdvertiseTypeRequest advertiseTypeRequest);

    AdvertiseType updateAdvertiseType(Integer id, AdvertiseTypeRequest advertiseTypeRequest);

    void deleteAdvertiseType(Integer id);
}
