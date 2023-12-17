package com.group6.ads.controllers.locations.models;

import com.group6.ads.repositories.database.images.ImageShort;
import com.group6.ads.repositories.database.locations.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationDetails {
    Optional<Location> location;
    List<ImageShort> listImage;
}