package com.emssanareps.affiliate.manager.enums;

import lombok.Getter;
import org.mapstruct.Named;

import java.util.Arrays;

@Getter
public enum ContactType {
    CELULAR("CELULAR"),
    CORREO("CORREO"),
    TELEFONO("TELEFONO");

    private final String name;

    ContactType(String name) {
        this.name = name;
    }

    public static ContactType fromValue(String value) {
        return Arrays.stream(ContactType.values())
                .filter(e -> e.getName().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo de contacto no valido: " + value));
    }
}
