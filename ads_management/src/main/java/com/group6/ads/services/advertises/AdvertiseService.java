package com.group6.ads.services.advertises;

import com.group6.ads.controllers.advertises.models.AdvertiseRequest;
import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.repositories.database.properties.Property;
import com.group6.ads.util.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * com.group6.ads.services.advertise
 * Create by Dang Ngoc Tien
 * Date 12/18/2023 - 11:26 PM
 * Description: ...
 */
public interface AdvertiseService {

    Page<Advertise> findAll(String search, PageRequestCustom pageRequestCustom);
    Page<Advertise> findAllByLocationId(Integer locationId, String search, PageRequestCustom pageRequestCustom);

    Advertise findById(Integer id);
    Advertise create(Integer locationId, AdvertiseRequest advertiseRequest);

    Advertise updateByRoot(Integer advertiseId, AdvertiseRequest advertiseRequest);

    void delete(Integer advertiseId);
}
