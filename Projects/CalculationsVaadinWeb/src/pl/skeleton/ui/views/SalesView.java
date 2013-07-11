/**
 * DISCLAIMER
 * 
 * The quality of the code is such that you should not copy any of it as best
 * practice how to build Vaadin applications.
 * 
 * @author jouni@vaadin.com
 * 
 */

package pl.skeleton.ui.views;

import java.awt.Color;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class SalesView extends VerticalLayout implements View {
	public static final String VIEW_NAME = "SalesView";

    Color[] colors = new Color[] { new Color(52, 154, 255),
            new Color(242, 81, 57), new Color(255, 201, 35),
            new Color(83, 220, 164) };
    int colorIndex = -1;

    @Override
    public void enter(ViewChangeEvent event) {
        setSizeFull();
        addStyleName("timeline");

        Label header = new Label("Revenue by Movie Title");
        header.addStyleName("h1");
        addComponent(header);

        HorizontalLayout toolbar = new HorizontalLayout();
        toolbar.setWidth("100%");
        toolbar.setSpacing(true);
        toolbar.setMargin(true);
        toolbar.addStyleName("toolbar");
        addComponent(toolbar);

        final ComboBox movieSelect = new ComboBox();
        movieSelect.setWidth("300px");
        toolbar.addComponent(movieSelect);
        movieSelect.addShortcutListener(new ShortcutListener("Add", KeyCode.ENTER, null) {

            @Override
            public void handleAction(Object sender, Object target) {
//                addSelectedMovie(movieSelect);
            }
        });

        Button add = new Button("Add");
        add.addStyleName("default");
        add.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                //addSelectedMovie(movieSelect);
            }
        });
        toolbar.addComponent(add);
        toolbar.setComponentAlignment(add, Alignment.BOTTOM_LEFT);
    }
}
