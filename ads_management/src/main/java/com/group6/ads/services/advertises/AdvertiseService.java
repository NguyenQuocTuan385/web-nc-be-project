package com.group6.ads.services.advertises;

import com.group6.ads.controllers.advertises.models.AdvertiseLicensingRequest;
import com.group6.ads.controllers.advertises.models.AdvertiseRequest;
import com.group6.ads.controllers.advertises.models.AdvertiseStatusRequest;
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
    Page<Advertise> findAllByLocationId(Integer locationId, String search, PageRequestCustom pageRequestCustom);

    Page<Advertise> findAllUnauthorizedAdvertisements(Integer propertyId,Integer parentId,String search, PageRequestCustom pageRequestCustom);

    Advertise create(Integer locationId, AdvertiseRequest advertiseRequest);

    Advertise getById(Integer advertiseId);

    Advertise updateByRoot(Integer advertiseId, AdvertiseRequest advertiseRequest);


    Advertise updateLicense(Integer advertiseId, AdvertiseLicensingRequest advertiseRequest);

    Advertise updateStatus(Integer advertiseId, AdvertiseStatusRequest advertiseStatusRequest);




    void deleteAdvertiseEdit(Integer advertiseEditId);


    void delete(Integer advertiseId);
}
