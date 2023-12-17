package com.group6.ads.services.advertise.forms;

import com.group6.ads.controllers.advertise.forms.models.AdvertiseFormRequest;
import com.group6.ads.repositories.database.advertise.forms.AdvertiseForm;
import com.group6.ads.util.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * com.group6.ads.services.advertise.forms
 * Create by Dang Ngoc Tien
 * Date 12/17/2023 - 9:18 AM
 * Description: ...
 */
public interface AdvertiseFormService {
    Page<AdvertiseForm> findAll(String search, PageRequestCustom pageRequestCustom);

    AdvertiseForm create(AdvertiseFormRequest advertiseFormRequest);

    AdvertiseForm update(Integer id, AdvertiseFormRequest advertiseFormRequest);

    void delete(Integer id);
}
