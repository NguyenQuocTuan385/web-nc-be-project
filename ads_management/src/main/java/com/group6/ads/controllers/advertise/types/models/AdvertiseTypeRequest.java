package com.group6.ads.controllers.advertise.types.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * com.group6.ads.controllers.advetise.types.models
 * Create by Dang Ngoc Tien
 * Date 12/12/2023 - 11:40 PM
 * Description: ...
 */
@Data
public class AdvertiseTypeRequest {
    @NotBlank(message = "name is not blank")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50")
    private String name;
    @NotBlank(message = "name is not blank")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50")
    private String description;
}
