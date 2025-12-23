package org.parttio;

import com.vaadin.flow.component.Component;
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

    LeaderLine(UI ui, Component from, Component to, LeaderLineOptions options) {
        this.ui = ui;
        String optionsJson = mapper.writeValueAsString(options);
        UI.getCurrent().getPage().executeJs("""
                const options = %s;
                window.leaderLine($0, $1, $2, options);
                """.formatted(optionsJson), id.toString(), from.getElement(), to.getElement());

        from.addDetachListener(e -> {
            remove();
        });

        to.addDetachListener(e -> {
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
