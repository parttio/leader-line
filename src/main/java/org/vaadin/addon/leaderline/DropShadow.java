package org.vaadin.addon.leaderline;

public record DropShadow(
        String color,
        Double dx,
        Double dy,
        Double blur
) {
    public static DropShadow of(String color, Double dx, Double dy, Double blur) {
        return new DropShadow(color, dx, dy, blur);
    }
}