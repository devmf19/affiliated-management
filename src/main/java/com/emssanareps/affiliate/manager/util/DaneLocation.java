package com.emssanareps.affiliate.manager.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DaneLocation {
    @JsonProperty("region")
    private String region;

    @JsonProperty("c_digo_dane_del_departamento")
    private String departmentCode;

    @JsonProperty("departamento")
    private String departmentName;

    @JsonProperty("c_digo_dane_del_municipio")
    private String municipalityCode;

    @JsonProperty("municipio")
    private String municipalityName;
}
