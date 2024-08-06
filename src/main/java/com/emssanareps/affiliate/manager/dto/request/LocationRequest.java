package com.emssanareps.affiliate.manager.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LocationRequest {
    private String address;
    private String description;
    private Long daneMunicipalityCode;
    private Long daneDepartmentCode;
}
