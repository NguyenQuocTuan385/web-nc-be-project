package com.group6.ads.controllers.admin.locations.models;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LocationStatusRequest {
    @NotNull
    private Boolean statusEdit;

    private Integer locationEditId;
}
