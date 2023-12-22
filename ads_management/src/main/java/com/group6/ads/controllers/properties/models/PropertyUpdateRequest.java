package com.group6.ads.controllers.properties.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PropertyUpdateRequest {
    @NotBlank(message = "name is not blank")
    @Size(min = 1, max = 255, message = "Code must be between 1 and 255")
    private String name;
    @Size(min = 1, max = 255, message = "Code must be between 1 and 255")
    private String code;
}
