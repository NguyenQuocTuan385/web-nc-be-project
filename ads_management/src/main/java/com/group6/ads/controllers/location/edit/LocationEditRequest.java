package com.group6.ads.controllers.location.edit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.group6.ads.repositories.database.images.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationEditRequest {
    private Boolean planning;
    private Double latitude;
    private Double longitude;
    private String address;
    private String content;
    private Integer propertyId;
    private Integer AdsFormId;
    private Integer locationTypeId;
    private Integer userId;
    private List<String> images;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}
