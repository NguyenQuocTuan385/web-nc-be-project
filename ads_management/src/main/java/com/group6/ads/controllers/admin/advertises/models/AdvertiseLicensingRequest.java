package com.group6.ads.controllers.admin.advertises.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdvertiseLicensingRequest
{
    @NotNull
    private Boolean licensing;
}
