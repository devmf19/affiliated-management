package com.emssanareps.affiliate.manager.service;

import com.emssanareps.affiliate.manager.dto.request.LocationRequest;
import com.emssanareps.affiliate.manager.util.DaneLocation;

import java.util.List;
import java.util.Optional;

public interface DaneService {
    List<DaneLocation> fetchDaneLocations();
    public DaneLocation findLocationByCodes(Long departmentCode, Long municipalityCode);
    boolean validateDaneCodes(LocationRequest locationRequest);
}
