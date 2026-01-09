package org.vaadin.addon.leaderline;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PathType {
    STRAIGHT, ARC, FLUID, MAGNET;

    @JsonValue
    public String json() {
        return name().toLowerCase();
    }
}
