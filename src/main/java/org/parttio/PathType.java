package org.parttio;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PathType {
    STRAIGHT, ARC, FLUID, MAGNET;

    @JsonValue
    public String json() {
        return name().toLowerCase();
    }
}
