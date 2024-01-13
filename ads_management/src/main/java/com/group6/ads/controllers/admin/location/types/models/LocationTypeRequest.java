package com.group6.ads.controllers.admin.location.types.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * com.group6.ads.controllers.location.types.models
 * Create by Dang Ngoc Tien
 * Date 12/19/2023 - 10:34 PM
 * Description: ...
 */
@Data
public class LocationTypeRequest {
    @NotBlank(message = "name is not blank")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50")
    private String name;
    @NotBlank(message = "description is not blank")
    @Size(min = 1, max = 256, message = "description must be between 1 and 256")
    private String description;
}
