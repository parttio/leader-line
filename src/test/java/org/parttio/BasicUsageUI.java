package org.parttio;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.Route;
import org.vaadin.firitin.components.orderedlayout.VVerticalLayout;
import org.vaadin.firitin.rad.AutoForm;
import org.vaadin.firitin.rad.AutoFormContext;

@Route
public class BasicUsageUI extends VVerticalLayout {

    private LeaderLine leaderLine;
    private LeaderLineOptions options;

    public BasicUsageUI() {

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

        options = new LeaderLineOptions() {{
            setColor("#ff00ff");
            setStartPlug(PlugType.SQUARE);
            setEndPlug(PlugType.ARROW2);
            setDash(new Dash(true, 100.0));
            setEndPlugSize(3.0);
            setEndPlugColor("green");
            setStartSocket(SocketType.BOTTOM);
            setDropShadow(new DropShadow("blue", 2.0, 2.0, 2.5));
            setStartLabel("Start label");
            setMiddleLabel("Middle label");
            setEndLabel("End label");
            setGradient(new Gradient("#ff0000", "#00ff00", 0.0));
            setSize(10.0);
            setOutline(true);
            setOutlineSize(5.0);
            setOutlineColor("red");
            setPath(PathType.ARC);
        }};

        leaderLine = LeaderLineFactory.drawLine(button, buttonTwo, options);

        add(button, buttonTwo, new Button("Random places", event -> {
            button.getStyle().setLeft((200+ Math.random() * 400) + "px");
            button.getStyle().setTop(Math.random() * 800 + "px");
            buttonTwo.getStyle().setLeft((200+ Math.random() * 400) + "px");
            buttonTwo.getStyle().setTop(Math.random() * 800 + "px");

            // notify the lineFactory to re-position
            leaderLine.position();
        }));


        add(new Button("Edit configs", e-> {

            AutoFormContext context = new AutoFormContext();
            // TODO configure to support nested objects, either here or in Viritin
            AutoForm<LeaderLineOptions> autoForm = context.createForm(options);
            autoForm.setSaveHandler(config -> {
                leaderLine.remove();
                leaderLine = LeaderLineFactory.drawLine(button, buttonTwo, options);
            });
            autoForm.setResetHandler(o -> {});
            autoForm.openInDialog();
        }));
        add(new Button("Default configs", e-> {
            options = new LeaderLineOptions();
            // Note, setOptions only adds new options, it does not reset
            //leaderLine.setOptions(options);
            leaderLine.remove();
            leaderLine = LeaderLineFactory.drawLine(button, buttonTwo, options);
        }));
    }
}
