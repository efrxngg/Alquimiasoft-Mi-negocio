package com.alquimiasoft.minegocio.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum IdentificationType {
    RUC("RUC"),
    CED("CED");
    private final String type;

    IdentificationType(String type) {
        this.type = type;
    }

    public static IdentificationType fromString(String type) {
        for (IdentificationType t : IdentificationType.values()) {
            if (t.type.equals(type))
                return t;
        }
        throw new IllegalArgumentException("Unexpeted value: " + type);
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(type);
    }
}
