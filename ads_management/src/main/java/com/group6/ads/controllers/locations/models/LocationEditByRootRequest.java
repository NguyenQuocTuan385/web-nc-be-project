package com.group6.ads.controllers.locations.models;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LocationEditByRootRequest {
    private Boolean planning;
    private Boolean statusEdit;
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
    private String imageUrls;
    private Integer locationEditId;
}
