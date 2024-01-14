package com.group6.ads.controllers.admin.locations.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
/**
 * com.group6.ads.controllers.locations.models
 * Create by Dang Ngoc Tien
 * Date 12/17/2023 - 11:34 PM
 * Description: ...
 */
@Data
public class LocationCreateRequest {
    private Boolean planning;
    @NotNull(message = "latitude must not be null")
    private Double latitude;
    @NotNull(message = "longitude must not be null")
    private Double longitude;
    @NotBlank
    private String address;
    @NotNull(message = "AdsFormId must not be null")
    private Integer advertiseFormId;
    @NotNull(message = "locationTypeId must not be null")
    private Integer locationTypeId;
    @NotNull(message = "propertyId must not be null")
    private Integer propertyId;
    private String image;
}
