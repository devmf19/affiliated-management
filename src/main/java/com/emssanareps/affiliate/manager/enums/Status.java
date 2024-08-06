package com.emssanareps.affiliate.manager.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Status {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    String mapStatus(Status status) {
        return status != null ? status.getName() : null;
    }

    public static Status fromValue(String value) {
        return Arrays.stream(Status.values())
                .filter(e -> e.getName().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Estado no valido: " + value));
    }
}
