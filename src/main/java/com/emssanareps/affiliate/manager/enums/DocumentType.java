package com.emssanareps.affiliate.manager.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum DocumentType {
    TI("TARJETAIDENTIDAD"),
    CC("CEDULA CIUDADANIA"),
    CE("CEDULA EXTRANJERIA"),
    PASAPORTE("PASAPORTE")
    ;

    private final String name;

    DocumentType(String name) {
        this.name = name;
    }

    public String mapDocumentType(Regime regime) {
        return regime != null ? regime.getName() : null;
    }

    public static DocumentType fromValue(String value) {
        return Arrays.stream(DocumentType.values())
                .filter(e -> e.getName().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo de documento no valido: " + value));
    }
}
