package org.parttio;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;

import java.util.UUID;

/**
 * A simple wrapper for the LeaderLine library. Construct instances via LeaderLineFactory.
 */
public class LeaderLine {
    public static ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private final UUID id = UUID.randomUUID();
    private UI ui;


    LeaderLine(UI ui, Component from, Component to, LeaderLineOptions options) {
        this.ui = ui;
        try {
            String optionsJson = mapper.writeValueAsString(options);
            UI.getCurrent().getPage().executeJs("""
                    const options = %s;
                    window.leaderLine($0, $1, $2, options);
                    """.formatted(optionsJson), id.toString(), from.getElement(), to.getElement());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public void position() {
        ui.getPage().executeJs("""
                window.leaderLines[$0].position();
                """, id.toString());
    }

    public void setOptions(LeaderLineOptions options) {
        try {
            String optionsJson = mapper.writeValueAsString(options);
            ui.getPage().executeJs("""
                const options = %s;
                window.leaderLines[$0].setOptions(options);
                """.formatted(optionsJson), id.toString());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
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
