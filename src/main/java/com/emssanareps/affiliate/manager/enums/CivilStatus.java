package com.emssanareps.affiliate.manager.enums;

import lombok.Getter;
import org.mapstruct.Named;

import java.util.Arrays;

@Getter
public enum CivilStatus {
    SOLTERO("SOLTERO(A)"),
    CASADO("CASAD(A)"),
    UNION_LIBRE("UNION LIBRE");

    private final String name;

    CivilStatus(String name) {
        this.name = name;
    }

    public static CivilStatus fromValue(String value) {
        return Arrays.stream(CivilStatus.values())
                .filter(e -> e.getName().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Estado civil no valido: " + value));
    }
    
}
