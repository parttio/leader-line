package org.parttio;

import com.fasterxml.jackson.annotation.JsonValue;

public record SocketGravity (int... values) {

    @JsonValue
    public Object json() {
        if (values.length == 0) {
            return null;
        }
        if (values.length == 1) {
            return values[0];
        }
        return values;
    }

}
