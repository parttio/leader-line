package org.vaadin.addon.leaderline;

import tools.jackson.databind.module.SimpleModule;

public class ColorModule extends SimpleModule {
    public ColorModule() {
        addSerializer(new ColorSerializer());
    }
}
