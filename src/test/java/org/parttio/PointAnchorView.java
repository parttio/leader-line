package org.parttio;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.Route;
import org.vaadin.addon.leaderline.LeaderLine;
import org.vaadin.addon.leaderline.LeaderLineAnchor;
import org.vaadin.addon.leaderline.LeaderLineFactory;
import org.vaadin.firitin.components.orderedlayout.VVerticalLayout;

@Route
public class PointAnchorView extends VVerticalLayout {

    private LeaderLine leaderLine;

    public PointAnchorView() {

        Button button = new Button("Click here", e -> {
        }) {{
            Style style = getStyle();
            style.setPosition(Style.Position.ABSOLUTE);
            style.setLeft("200px");
            style.setTop("50px");
        }};

        Button buttonTwo = new Button("Another button", e -> {
        }) {{
            Style style = getStyle();
            style.setPosition(Style.Position.ABSOLUTE);
            style.setLeft("300px");
            style.setTop("300px");
        }};


        LeaderLineAnchor a2 = LeaderLineFactory.pointAnchor(buttonTwo, 30, 10);
        leaderLine = LeaderLineFactory.drawLine(button, a2);
        //leaderLine = LeaderLineFactory.drawLine(button, buttonTwo);

        add(button, buttonTwo, new Button("Random places", event -> {
            button.getStyle().setLeft((200 + Math.random() * 400) + "px");
            button.getStyle().setTop(Math.random() * 800 + "px");
            buttonTwo.getStyle().setLeft((200 + Math.random() * 400) + "px");
            buttonTwo.getStyle().setTop(Math.random() * 800 + "px");

            // notify the lineFactory to re-position
            leaderLine.position();
        }));

    }

}
