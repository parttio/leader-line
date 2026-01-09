package org.vaadin.addon.leaderline;

public record Dash(boolean enabled, Double animation) {

    public Dash(boolean enabled) {
        this(enabled, null);
    }

}