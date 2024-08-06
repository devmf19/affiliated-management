package com.emssanareps.affiliate.manager.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum CivilStatus {
    SOLTERO("FEMENINO"),
    CASADO("MASCULINO"),
    UNION_LIBRE("UNION LIBRE");

    private final String name;

    CivilStatus(String name) {
        this.name = name;
    }
    
    public String mapRegime(Regime regime) {
        return regime != null ? regime.getName() : null;
    }

    public static CivilStatus fromValue(String value) {
        return Arrays.stream(CivilStatus.values())
                .filter(e -> e.getName().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Estado civil no valido: " + value));
    }
    
}
