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

import java.util.Date;

import org.vaadin.cssinject.CSSInject;

import com.vaadin.addon.calendar.ui.Calendar;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClick;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClickHandler;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ScheduleView extends CssLayout implements View {
	public static final String VIEW_NAME = "ScheduleView";
    private CssLayout catalog;
    private Calendar cal;
    private Window popup;
    private CSSInject css;
    private HorizontalLayout tray;
    
    @Override
    public void enter(ViewChangeEvent event) {
        setSizeFull();
        addStyleName("schedule");

        css = new CSSInject(UI.getCurrent());

        TabSheet tabs = new TabSheet();
        tabs.setSizeFull();
        tabs.addStyleName("borderless");
        addComponent(tabs);
        tabs.addComponent(buildCalendarView());

        catalog = new CssLayout();
        catalog.setCaption("Catalog");
        catalog.addStyleName("catalog");
        tabs.addComponent(catalog);
    }

    private Component buildCalendarView() {
        VerticalLayout calendarLayout = new VerticalLayout();
        calendarLayout.setCaption("Calendar");
        calendarLayout.addStyleName("dummy");
        calendarLayout.setMargin(true);

        cal = new Calendar();
        cal.setWidth("100%");
        cal.setHeight("1000px");

         cal.setStartDate(new Date());
         cal.setEndDate(new Date());

        cal.setHandler(new EventClickHandler() {
            @Override
            public void eventClick(EventClick event) {
                hideTray();
                getUI().removeWindow(popup);
                getUI().addWindow(popup);
                popup.focus();
            }
        });
        calendarLayout.addComponent(cal);

        cal.setFirstVisibleHourOfDay(11);
        cal.setLastVisibleHourOfDay(23);

        return calendarLayout;
    }

    void buildTray() {
        if (tray != null)
            return;

        tray = new HorizontalLayout();
        tray.setWidth("100%");
        tray.addStyleName("tray");
        tray.setSpacing(true);
        tray.setMargin(true);

        Label warning = new Label(
                "You have unsaved changes made to the schedule");
        warning.addStyleName("warning");
        warning.addStyleName("icon-attention");
        tray.addComponent(warning);
        tray.setComponentAlignment(warning, Alignment.MIDDLE_LEFT);
        tray.setExpandRatio(warning, 1);

        ClickListener close = new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                tray.removeStyleName("v-animate-reveal");
                tray.addStyleName("v-animate-hide");
            }
        };

        Button confirm = new Button("Confirm");
        confirm.addStyleName("wide");
        confirm.addStyleName("default");
        confirm.addClickListener(close);
        tray.addComponent(confirm);
        tray.setComponentAlignment(confirm, Alignment.MIDDLE_LEFT);

        Button discard = new Button("Discard");
        discard.addStyleName("wide");
        discard.addClickListener(close);
        tray.addComponent(discard);
        tray.setComponentAlignment(discard, Alignment.MIDDLE_LEFT);
    }

    void showTray() {
        buildTray();
        tray.removeStyleName("v-animate-hide");
        tray.addStyleName("v-animate-reveal");
        addComponent(tray);
    }

    void hideTray() {
        if (tray != null)
            removeComponent(tray);
    }

}
