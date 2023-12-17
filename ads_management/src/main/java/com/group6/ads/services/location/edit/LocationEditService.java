package com.group6.ads.services.location.edit;

import com.group6.ads.controllers.location.edit.LocationEditRequest;
import com.group6.ads.repositories.database.location.edit.LocationEdit;

public interface LocationEditService {
    public LocationEdit createRequestEditLocation(LocationEditRequest locationEditRequest);
}
