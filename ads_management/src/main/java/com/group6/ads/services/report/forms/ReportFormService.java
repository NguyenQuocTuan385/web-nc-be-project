package com.group6.ads.services.report.forms;

import com.group6.ads.controllers.report.forms.models.ReportFormRequest;
import com.group6.ads.repositories.database.report.forms.ReportForm;

import java.util.List;

/**
 * com.group6.ads.services.report.forms
 * Create by Dang Ngoc Tien
 * Date 12/12/2023 - 1:13 PM
 * Description: ...
 */
public interface ReportFormService {

    List<ReportForm> findAll();

    ReportForm create(ReportFormRequest reportFormRequest);

    ReportForm update(Integer id, ReportFormRequest reportFormRequest);

    void delete(Integer id);
}
