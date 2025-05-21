package org.parttio;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
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
        LeaderLine leaderLine = new LeaderLine(UI.getCurrent(), from, to, options);
        return leaderLine;
    }

    public static LeaderLine drawLine(Component from, Component to) {
        LeaderLine leaderLine = new LeaderLine(UI.getCurrent(), from, to, new LeaderLineOptions());
        return leaderLine;
    }
}
