package com.emssanareps.affiliate.manager.enums;

import lombok.Getter;
import org.mapstruct.Named;

import java.util.Arrays;

@Getter
public enum Regime {
    SUBSIDIADO("SUBSIDIADO"),
    CONTRIBUTIVO("CONTRIBUTIVO");

    private final String name;

    Regime(String name) {
        this.name = name;
    }

    public static Regime fromValue(String value) {
        return Arrays.stream(Regime.values())
                .filter(e -> e.getName().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Regimen no valido: " + value));
    }
}
