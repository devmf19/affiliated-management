package com.emssanareps.affiliate.manager.service.impl;

import com.emssanareps.affiliate.manager.constants.LocationConstants;
import com.emssanareps.affiliate.manager.dto.request.LocationRequest;
import com.emssanareps.affiliate.manager.mapper.LocationMapper;
import com.emssanareps.affiliate.manager.model.Location;
import com.emssanareps.affiliate.manager.repository.LocationRepository;
import com.emssanareps.affiliate.manager.service.DaneService;
import com.emssanareps.affiliate.manager.service.LocationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final DaneService daneService;
    private final LocationMapper locationMapper;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository, DaneService daneService, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.daneService = daneService;
        this.locationMapper = locationMapper;
    }

    @Override
    @Transactional
    public Location create(LocationRequest locationRequest) {
        if (daneService.validateDaneCodes(locationRequest))
            return locationRepository.save(locationMapper.toEntity(locationRequest));

        throw new IllegalArgumentException(LocationConstants.ADDRESS_ERROR);
    }

    @Override
    @Transactional
    public Location modify(Long locationId, LocationRequest locationRequest) {
        if (daneService.validateDaneCodes(locationRequest)) {

            Location old = locationRepository.findById(locationId).orElseThrow(
                    () -> new IllegalArgumentException(LocationConstants.SEARCH_ERROR)
            );

            Location updatedLocation = locationMapper.toEntity(locationRequest);
            updatedLocation.setId(old.getId());

            return locationRepository.save(updatedLocation);
        }

        throw new IllegalArgumentException(LocationConstants.ADDRESS_ERROR);
    }
}
