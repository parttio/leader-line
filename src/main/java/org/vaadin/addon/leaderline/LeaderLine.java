package org.vaadin.addon.leaderline;

import com.vaadin.flow.component.UI;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.util.UUID;

/**
 * A simple wrapper for the LeaderLine library. Construct instances via LeaderLineFactory.
 */
public class LeaderLine {

    public static ObjectMapper mapper = JsonMapper.builder()
            .addModule(new ColorModule())
            .build();

    private final UUID id = UUID.randomUUID();
    private UI ui;

    LeaderLine(UI ui, LeaderLineAnchor from, LeaderLineAnchor to, LeaderLineOptions options) {
        this.ui = ui;
        String optionsJson = mapper.writeValueAsString(options);
        UI.getCurrent().getPage().executeJs("""
                const options = %s;
                debugger;
                const a1 = window.leaderLineAnchors[$1.type]($1.element, $1.options);
                const a2 = window.leaderLineAnchors[$2.type]($2.element, $2.options);
                window.leaderLine($0, a1, a2, options);
                """.formatted(optionsJson), id.toString(), from, to);

        from.element().addDetachListener(e -> {
            remove();
        });

        to.element().addDetachListener(e -> {
            remove();
        });
    }

    public void position() {
        ui.getPage().executeJs("""
                window.leaderLines[$0].position();
                """, id.toString());
    }

    public void setOptions(LeaderLineOptions options) {
        String optionsJson = mapper.writeValueAsString(options);
        ui.getPage().executeJs("""
            const options = %s;
            window.leaderLines[$0].setOptions(options);
            """.formatted(optionsJson), id.toString());
    }

    /**
     * Remove the leader line from the web page. It can't be used anymore.
     */
    public void remove() {
        ui.getPage().executeJs("""
                window.leaderLines[$0].remove();
                delete window.leaderLines[$0];
                """, id.toString());
    }

}
