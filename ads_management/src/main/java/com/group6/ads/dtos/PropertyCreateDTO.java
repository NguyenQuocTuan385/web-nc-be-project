package com.group6.ads.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PropertyCreateDTO {

    @NotNull
    private Integer property_parent_id;
    @NotBlank(message = "name is not blank")
    @Size(min = 1, max = 255, message = "Code must be between 1 and 255")
    private String name;
    @NotBlank(message = "code is not blank")
    @Size(min = 1, max = 255, message = "Code must be between 1 and 255")
    private String code;
}
