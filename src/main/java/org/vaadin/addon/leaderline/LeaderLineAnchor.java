package org.vaadin.addon.leaderline;

import com.vaadin.flow.dom.Element;

public record LeaderLineAnchor(Element element, AnchorType type, Object options) {
    enum AnchorType {
        element, pointAnchor
    }
}
