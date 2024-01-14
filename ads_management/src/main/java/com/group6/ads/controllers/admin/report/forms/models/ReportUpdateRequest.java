package com.group6.ads.controllers.admin.report.forms.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReportUpdateRequest {
    private String reply;
    @NotNull(message = "status must not be null")
    private Integer status;
}
