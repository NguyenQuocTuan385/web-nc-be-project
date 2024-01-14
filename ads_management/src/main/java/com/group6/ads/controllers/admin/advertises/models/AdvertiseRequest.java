package com.group6.ads.controllers.admin.advertises.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * com.group6.ads.controllers.advertises.models
 * Create by Dang Ngoc Tien
 * Date 12/19/2023 - 1:49 AM
 * Description: ...
 */
@Data
public class AdvertiseRequest {
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
    @NotNull(message = "userId must not be null")
    private Integer userId;
    @NotBlank(message = "content must not be empty")
    private String content;
}