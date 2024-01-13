package com.group6.ads.controllers.admin.advertises.models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvertiseEditByRootRequest {
    @NotNull(message = "licensing must not be null")
    private Boolean licensing;
    @NotNull(message = "height must not be null")
    private Double height;
    @NotNull(message = "width must not be null")
    private Double width;
    @NotNull(message = "adsTypeId must not be null")
    private Integer adsTypeId;
    @NotNull(message = "locationId must not be null")
    private Integer locationId;
    @NotNull(message = "pillarQuantity must not be null")
    private Integer pillarQuantity;
    private String images;
}
