package org.parttio;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.Route;
import in.virit.color.Color;
import in.virit.color.HexColor;
import in.virit.color.NamedColor;
import org.vaadin.firitin.components.orderedlayout.VVerticalLayout;
import org.vaadin.firitin.rad.AutoForm;
import org.vaadin.firitin.rad.AutoFormContext;
import tools.jackson.databind.JavaType;

import java.util.Arrays;

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
            setColor(HexColor.of("#ff00ff"));
            setStartPlug(PlugType.SQUARE);
            setEndPlug(PlugType.ARROW2);
            setDash(new Dash(true, 100.0));
            setEndPlugSize(3.0);
            setEndPlugColor(NamedColor.GREEN);
            setStartSocket(SocketType.BOTTOM);
            setDropShadow(new DropShadow("blue", 2.0, 2.0, 2.5));
            // TOOD figure out why these don't work, dies totally in Safari
            //setStartLabel("Start label");
            //setMiddleLabel("Middle label");
            //setEndLabel("End label");
            setGradient(new Gradient(NamedColor.RED, NamedColor.GREEN, 0.0));
            setSize(10.0);
            setOutline(true);
            setOutlineSize(5.0);
            setOutlineColor(NamedColor.RED);
            // Start to the line towards bottom right
            setStartSocketGravity(new SocketGravity(200));
            setPath(PathType.FLUID);
            //setPath(PathType.MAGNET);
            //setPath(PathType.ARC);
        }};

        leaderLine = LeaderLineFactory.drawLine(button, buttonTwo, options);
        //leaderLine = LeaderLineFactory.drawLine(button, buttonTwo);

        add(button, buttonTwo, new Button("Random places", event -> {
            button.getStyle().setLeft((200 + Math.random() * 400) + "px");
            button.getStyle().setTop(Math.random() * 800 + "px");
            buttonTwo.getStyle().setLeft((200 + Math.random() * 400) + "px");
            buttonTwo.getStyle().setTop(Math.random() * 800 + "px");

            // notify the lineFactory to re-position
            leaderLine.position();
        }));

        add(new Button("Edit configs", e -> {

            AutoFormContext context = new AutoFormContext();
            context.withPropertyEditor(ctx -> {
                // TODO change AutoFormContext so that direct editors can be registered with
                // class-to-class pairs
                JavaType primaryType = ctx.beanPropertyDefinition().getPrimaryType();
                if (primaryType.getRawClass() == SocketGravity.class) {
                    return new SocketGravityField();
                }
                if (primaryType.getRawClass() == Color.class) {
                    return new CssColorField();
                }
                return null;
            });
            // TODO configure to support nested objects, either here or in Viritin
            AutoForm<LeaderLineOptions> autoForm = context.createForm(options);
            autoForm.setSaveHandler(config -> {
                leaderLine.remove();
                leaderLine = LeaderLineFactory.drawLine(button, buttonTwo, options);
            });
            autoForm.setResetHandler(o -> {
            });
            autoForm.openInDialog();
        }));
        add(new Button("Default configs", e -> {
            options = new LeaderLineOptions();
            // Note, setOptions only adds new options, it does not reset
            //leaderLine.setOptions(options);
            leaderLine.remove();
            leaderLine = LeaderLineFactory.drawLine(button, buttonTwo, options);
        }));
    }

    /* trivial field to edit CssColor */
    public static class CssColorField extends CustomField<Color> {
        private TextField textField = new TextField() {{
            setPlaceholder("e.g. rgba(255 0 0 / 0.5)");
        }};

        public CssColorField() {
            add(textField);
            textField.addValueChangeListener(event -> {
                // Propagate the event to custom field level...
                if (event.isFromClient()) {
                    setModelValue(generateModelValue(), true);
                }
            });
        }

        @Override
        protected Color generateModelValue() {
            return Color.parseCssColor(textField.getValue());
        }

        @Override
        protected void setPresentationValue(Color newPresentationValue) {
            textField.setValue(newPresentationValue.toString());
        }
    }

    /*
    * Custom field for SocketGravity, which can be int or int,int ...
     */
    public class SocketGravityField extends CustomField<SocketGravity> {

        private TextField textField = new TextField(){{
            setPlaceholder("\"int, int\" or \"int");
        }};

        public SocketGravityField() {
            add(textField);
            textField.addValueChangeListener(event -> {
                // Propagate the event to custom field level...
                if (event.isFromClient()) {
                    setModelValue(generateModelValue(), true);
                }
            });
        }

        @Override
        protected SocketGravity generateModelValue() {
            String value = textField.getValue();
            if (value == null || value.isEmpty()) {
                return null;
            }
            String[] values = value.split(",");
            if (values.length == 1) {
                return new SocketGravity(Integer.parseInt(values[0]));
            } else {
                return new SocketGravity(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
            }
        }

        @Override
        protected void setPresentationValue(SocketGravity newPresentationValue) {
            String str = Arrays.toString(newPresentationValue.values())
                    .replaceAll("\\[", "")
                    .replaceAll("]", "");

            textField.setValue(str);
        }
    }
}
