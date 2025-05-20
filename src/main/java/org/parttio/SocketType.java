package org.parttio;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SocketType {
    AUTO, TOP, BOTTOM, LEFT, RIGHT;
    @JsonValue
    public String json() {
        return name().toLowerCase();
    }

}
