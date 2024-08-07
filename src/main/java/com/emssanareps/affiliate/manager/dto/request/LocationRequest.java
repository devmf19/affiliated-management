package com.emssanareps.affiliate.manager.dto.request;

import com.emssanareps.affiliate.manager.constants.LocationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LocationRequest {

    @NotBlank(message = LocationConstants.REQUIRED_ADDRESS)
    private String address;

    private String description;

    @NotNull(message = LocationConstants.REQUIRED_DEPARTMENT_CODE)
    private Long daneMunicipalityCode;

    @NotNull(message = LocationConstants.REQUIRED_MUNICIPALITY_CODE)
    private Long daneDepartmentCode;
}
