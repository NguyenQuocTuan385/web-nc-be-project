package com.group6.ads.controllers.admin.advertises.models;

import lombok.Data;

/**
 * com.group6.ads.controllers.advertises.models
 * Create by Dang Ngoc Tien
 * Date 12/19/2023 - 1:49 AM
 * Description: ...
 */
@Data
public class AdvertiseRequest {
    private Boolean licensing;
    private Double height;
    private Double width;
    private Integer adsTypeId;
    private Integer locationId;
    private Integer pillarQuantity;
    private String images;
    private Integer userId;
    private String content;
}