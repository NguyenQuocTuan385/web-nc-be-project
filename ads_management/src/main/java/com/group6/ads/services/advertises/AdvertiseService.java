package com.group6.ads.services.advertises;

import com.group6.ads.controllers.advertises.models.AdvertiseRequest;
import com.group6.ads.repositories.database.advertises.Advertise;

import java.util.List;

/**
 * com.group6.ads.services.advertise
 * Create by Dang Ngoc Tien
 * Date 12/18/2023 - 11:26 PM
 * Description: ...
 */
public interface AdvertiseService {
    List<Advertise> findAllByLocationId(Integer locationId);

    Advertise updateLocation(Integer advertiseId, Integer locationId);

    Advertise create(Integer locationId, AdvertiseRequest advertiseRequest);
}
