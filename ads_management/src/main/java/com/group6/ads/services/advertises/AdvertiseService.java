package com.group6.ads.services.advertises;

import com.group6.ads.controllers.advertises.models.AdvertiseEditByRootRequest;
import com.group6.ads.controllers.advertises.models.AdvertiseEditRequest;
import com.group6.ads.controllers.advertises.models.AdvertiseLicensingRequest;
import com.group6.ads.controllers.advertises.models.AdvertiseRequest;
import com.group6.ads.controllers.advertises.models.AdvertiseStatusRequest;
import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.repositories.database.advertises.edit.AdvertiseEdit;
import com.group6.ads.util.PageRequestCustom;
import org.springframework.data.domain.Page;

/**
 * com.group6.ads.services.advertise
 * Create by Dang Ngoc Tien
 * Date 12/18/2023 - 11:26 PM
 * Description: ...
 */
public interface AdvertiseService {

    Page<Advertise> findAll(String search, PageRequestCustom pageRequestCustom);

    Page<Advertise> findAllByLocationId(Integer locationId, String search, PageRequestCustom pageRequestCustom);

    Page<Advertise> findAllUnauthorizedAdvertisements(Integer propertyId,Integer parentId,String search, PageRequestCustom pageRequestCustom);

//    Advertise updateByRoot(Integer advertiseId, AdvertiseRequest advertiseRequest);

    Advertise findById(Integer id);

    Advertise create(Integer locationId, AdvertiseRequest advertiseRequest);

    Advertise updateByRoot(Integer advertiseId, AdvertiseEditByRootRequest advertiseEditByRootRequest);

    Advertise updateLicense(Integer advertiseId, AdvertiseLicensingRequest advertiseRequest);

    Advertise updateStatus(Integer advertiseId, AdvertiseStatusRequest advertiseStatusRequest);

    void deleteAdvertiseEdit(Integer advertiseEditId);

    void delete(Integer advertiseId);

    AdvertiseEdit update(Integer advertiseId, AdvertiseEditRequest advertiseEditRequest);
}
