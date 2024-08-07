package com.emssanareps.affiliate.manager.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum BeneficiaryType {
    HIJX("HIJO(A)"),
    ESPOSX("ESPOS0(A)");

    private final String name;

    BeneficiaryType(String name) {
        this.name = name;
    }

    public String mapBeneficiaryType(Regime regime) {
        return regime != null ? regime.getName() : null;
    }

    public static BeneficiaryType fromValue(String value) {
        return Arrays.stream(BeneficiaryType.values())
                .filter(e -> e.getName().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo de beneficiario no valido: " + value));
    }
}
