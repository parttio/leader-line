package org.parttio;

public record Gradient(
        String startColor,
        String endColor,
        Double angle
) {
    public static Gradient of(String startColor, String endColor, Double angle) {
        return new Gradient(startColor, endColor, angle);
    }
}
