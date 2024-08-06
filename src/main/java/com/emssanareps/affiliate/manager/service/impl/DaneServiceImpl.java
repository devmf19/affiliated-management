package com.emssanareps.affiliate.manager.service.impl;
import com.emssanareps.affiliate.manager.dto.request.LocationRequest;
import com.emssanareps.affiliate.manager.service.DaneService;
import com.emssanareps.affiliate.manager.util.DaneLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class DaneServiceImpl implements DaneService {

    @Value("{dane.api.url}")
    private static String DANE_API_URL;
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

        String municipalityCodeStr = String.valueOf(locationRequest.getDaneMunicipalityCode());
        String departmentCodeStr = String.valueOf(locationRequest.getDaneDepartmentCode());

        if (!municipalityCodeStr.startsWith(departmentCodeStr)) {
            throw new IllegalArgumentException("El código del municipio no corresponde con el código del departamento.");
        }

        boolean isValid = daneData.stream()
                .anyMatch(data -> data.getMunicipalityCode().equals(municipalityCodeStr) &&
                        data.getDepartmentCode().equals(departmentCodeStr)
                );

        if (!isValid) {
            throw new IllegalArgumentException("El código del municipio o departamento no es válido.");
        }

        return true;
    }

}
