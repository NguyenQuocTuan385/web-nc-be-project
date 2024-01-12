package com.group6.ads.controllers.admin.advertises.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdvertiseStatusRequest {
    @NotNull
    private Boolean statusEdit;
    private Integer advertiseEditId;
}
