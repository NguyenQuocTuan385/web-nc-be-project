package com.group6.ads.controllers.advertise.forms.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * com.group6.ads.controllers.advertise.forms.models
 * Create by Dang Ngoc Tien
 * Date 12/17/2023 - 9:23 AM
 * Description: ...
 */
@Data
public class AdvertiseFormRequest {
    @NotBlank(message = "name is not blank")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50")
    private String name;
    @NotBlank(message = "description is not blank")
    @Size(min = 1, max = 256, message = "description must be between 1 and 50")
    private String description;
}
