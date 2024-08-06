package com.emssanareps.affiliate.manager.service;

import com.emssanareps.affiliate.manager.dto.request.LocationRequest;
import com.emssanareps.affiliate.manager.util.DaneLocation;

import java.util.List;

public interface DaneService {
    List<DaneLocation> fetchDaneLocations();
    boolean validateDaneCodes(LocationRequest locationRequest);
}
