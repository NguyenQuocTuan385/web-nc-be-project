package com.group6.ads.controllers.location.edit.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.group6.ads.repositories.database.images.Image;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LocationEditRequest {
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
    @NotNull(message = "userId must not be null")
    private Integer userId;
    @NotBlank
    private String content;
}
