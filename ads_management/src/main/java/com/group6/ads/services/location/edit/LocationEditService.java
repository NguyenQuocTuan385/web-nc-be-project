package com.group6.ads.services.location.edit;

import com.group6.ads.controllers.location.edit.models.LocationEditRequest;
import com.group6.ads.repositories.database.location.edit.LocationEdit;
import lombok.NonNull;

public interface LocationEditService {
    LocationEdit update(Integer locationId, LocationEditRequest locationEditRequest);
}
