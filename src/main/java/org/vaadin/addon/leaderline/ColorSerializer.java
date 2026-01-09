package org.vaadin.addon.leaderline;

import in.virit.color.Color;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

public class ColorSerializer extends StdSerializer<Color> {
    protected ColorSerializer() {
        super(Color.class);
    }

    @Override
    public void serialize(Color value, JsonGenerator gen, SerializationContext ctxt) throws JacksonException {
        String string = value.toRgbColor().legacyRgba();
        gen.writeString(string);
    }
}
