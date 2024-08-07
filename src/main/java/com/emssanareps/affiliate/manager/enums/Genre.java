package com.emssanareps.affiliate.manager.enums;

import lombok.Getter;
import org.mapstruct.Named;

import java.util.Arrays;

@Getter
public enum Genre {
    FEMENINO("FEMENINO"),
    MASCULINO("MASCULINO");

    private final String name;

    Genre(String name) {
        this.name = name;
    }

    public static Genre fromValue(String value) {
        return Arrays.stream(Genre.values())
                .filter(e -> e.getName().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Genero no valido: " + value));
    }
}
