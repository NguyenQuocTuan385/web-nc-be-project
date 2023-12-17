package com.group6.ads.services.advertise.forms;

import com.group6.ads.controllers.advertise.forms.models.AdvertiseFormRequest;
import com.group6.ads.repositories.database.advertise.forms.AdvertiseForm;
import jakarta.transaction.Transactional;

import java.util.List;

/**
 * com.group6.ads.services.advertise.forms
 * Create by Dang Ngoc Tien
 * Date 12/17/2023 - 9:18 AM
 * Description: ...
 */
public interface AdvertiseFormService {
    List<AdvertiseForm> findAll();

    @Transactional
    AdvertiseForm create(AdvertiseFormRequest advertiseFormRequest);

    @Transactional
    AdvertiseForm update(Integer id, AdvertiseFormRequest advertiseFormRequest);

    @Transactional
    void delete(Integer id);
}
