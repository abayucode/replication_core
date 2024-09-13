package com.uph.replication.core.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum UnitSizeEnums {
    MIL("ml"),
    KILOGRAM("kg"),
    METERS("m"),
    AMPERE("ampere");

    private String value;

    UnitSizeEnums(String value) {
        this.value = value;
    }
}
