package com.emssanareps.affiliate.manager.service.impl;
import com.emssanareps.affiliate.manager.constants.LocationConstants;
import com.emssanareps.affiliate.manager.dto.request.LocationRequest;
import com.emssanareps.affiliate.manager.service.DaneService;
import com.emssanareps.affiliate.manager.util.DaneLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class DaneServiceImpl implements DaneService {

    @Value("${dane.api.url}")
    private String DANE_API_URL;
    private final RestTemplate restTemplate;

    @Autowired
    public DaneServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<DaneLocation> fetchDaneLocations() {
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(DANE_API_URL, DaneLocation[].class))).toList();
    }

    @Override
    public boolean validateDaneCodes(LocationRequest locationRequest) {
        List<DaneLocation> daneData = fetchDaneLocations();

        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);

        String municipalityCodeStr = decimalFormat.format(locationRequest.getDaneMunicipalityCode());

        String departmentCodeStr = String.valueOf(locationRequest.getDaneDepartmentCode());

        if (!municipalityCodeStr.startsWith(departmentCodeStr)) {
            throw new IllegalArgumentException(LocationConstants.MUNICIPALITY_DEPARTMENT_ERROR);
        }

        boolean isValid = daneData.stream()
                .anyMatch(data -> data.getMunicipalityCode().equals(municipalityCodeStr) &&
                        data.getDepartmentCode().equals(departmentCodeStr)
                );

        if (!isValid) {
            throw new IllegalArgumentException(LocationConstants.ERROR_CODE);
        }

        return true;
    }

}
