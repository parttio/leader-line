package org.parttio;

import in.virit.color.Color;

public record Gradient(
        Color startColor,
        Color endColor,
        Double angle
) {
    public static Gradient of(Color startColor, Color endColor, Double angle) {
        return new Gradient(startColor, endColor, angle);
    }
}
