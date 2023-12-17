package com.group6.ads.controllers.roles.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleRequest {

    @NotBlank(message = "Name must be not blank")
    @Size(min = 1, max = 10, message = "Code must be between 1 and 50")
    private String code;

    @NotBlank(message = "Name must be not blank")
    @Size(min = 1, max = 100, message = "Code must be between 1 and 50")
    private String description;


}
