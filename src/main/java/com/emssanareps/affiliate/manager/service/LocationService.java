package com.emssanareps.affiliate.manager.service;

import com.emssanareps.affiliate.manager.dto.request.LocationRequest;
import com.emssanareps.affiliate.manager.model.Location;

public interface LocationService {
    Location create(LocationRequest locationRequest);
}
