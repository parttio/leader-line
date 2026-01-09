package org.vaadin.addon.leaderline;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

/**
 * A simple wrapper for the LeaderLine library.
 * <p>
 *     Because of "reasons" you might need to use @Uses(LeaderLine.class) in your main layout.
 * </p>
 * <p>
 *  See also: https://github.com/II-alex-II/leader-line-new
 * </p>
 * <p>
 *     Implementation note: This class implements class only to hack around Framework to load
 *     the JS library. The actual implementation is not a component tied to a DOM element.
 * </p>
 */
@NpmPackage(value = "leader-line-new", version = "1.1.9")
@JsModule("./leader-line-addon/leaderline.js")
public class LeaderLineFactory extends Component {

    private LeaderLineFactory() {
    }

    public static LeaderLine drawLine(Component from, Component to, LeaderLineOptions options) {
        LeaderLineAnchor a1 = new LeaderLineAnchor(from.getElement(), LeaderLineAnchor.AnchorType.element, null);
        LeaderLineAnchor a2 = new LeaderLineAnchor(to.getElement(), LeaderLineAnchor.AnchorType.element, null);
        return drawLine(a1, a2, options);
    }

    public static LeaderLine drawLine(Component from, LeaderLineAnchor to, LeaderLineOptions options) {
        LeaderLineAnchor a1 = new LeaderLineAnchor(from.getElement(), LeaderLineAnchor.AnchorType.element, null);
        return drawLine(a1, to, options);
    }

    public static LeaderLine drawLine(LeaderLineAnchor from, Component to, LeaderLineOptions options) {
        LeaderLineAnchor a2 = new LeaderLineAnchor(to.getElement(), LeaderLineAnchor.AnchorType.element, null);
        return drawLine(from, a2, options);
    }

    public static LeaderLine drawLine(LeaderLineAnchor from, LeaderLineAnchor to, LeaderLineOptions options) {
        LeaderLine leaderLine = new LeaderLine(UI.getCurrent(), from, to, options);
        return leaderLine;
    }

    public static LeaderLine drawLine(Component from, LeaderLineAnchor to) {
        LeaderLineAnchor a1 = new LeaderLineAnchor(from.getElement(), LeaderLineAnchor.AnchorType.element, null);
        return drawLine(a1, to, new LeaderLineOptions());
    }

    public static LeaderLine drawLine(LeaderLineAnchor from, Component to) {
        LeaderLineAnchor a2 = new LeaderLineAnchor(to.getElement(), LeaderLineAnchor.AnchorType.element, null);
        return drawLine(from, a2, new LeaderLineOptions());
    }

    public static LeaderLine drawLine(LeaderLineAnchor from, LeaderLineAnchor to) {
        LeaderLine leaderLine = new LeaderLine(UI.getCurrent(), from, to, new LeaderLineOptions());
        return leaderLine;
    }

    public static LeaderLine drawLine(Component from, Component to) {
        return drawLine(from, to, new LeaderLineOptions());
    }

    record XY(int x, int y) {}

    public static LeaderLineAnchor pointAnchor(Button component, int x, int y) {
        return new LeaderLineAnchor(component.getElement(), LeaderLineAnchor.AnchorType.pointAnchor, new XY(x,y));
   }
}
