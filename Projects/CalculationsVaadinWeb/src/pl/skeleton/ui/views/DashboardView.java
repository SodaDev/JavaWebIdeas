package pl.skeleton.ui.views;

import java.text.DecimalFormat;

import pl.skeleton.ui.DashboardUI;

import com.vaadin.data.Property;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.Table.RowHeaderMode;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class DashboardView extends VerticalLayout implements View {
	private static final long serialVersionUID = -4071854876711456470L;
	public static final String VIEW_NAME = "DashboardView";
	private String dashboardTitle = "View title";
	private Window notifications;
	
    public DashboardView() {
        setSizeFull();
        addStyleName("dashboard-view");

        createTopToolBar();
        createContent();
    }

    private void createContent() {
    	HorizontalLayout row = new HorizontalLayout();
        row.setSizeFull();
        row.setMargin(new MarginInfo(true, true, false, true));
        row.setSpacing(true);
        addComponent(row);
        setExpandRatio(row, 1.5f);

        //row.addComponent(createPanel(new TopGrossingMoviesChart()));

        TextArea notes = new TextArea("Notes");
        notes.setValue("Remember to:\n· Zoom in and out in the Sales view\n· Filter the transactions and drag a set of them to the Reports tab\n· Create a new report\n· Change the schedule of the movie theater");
        notes.setSizeFull();
        CssLayout panel = createPanel(notes);
        panel.addStyleName("notes");
        row.addComponent(panel);

        row = new HorizontalLayout();
        row.setMargin(true);
        row.setSizeFull();
        row.setSpacing(true);
        addComponent(row);
        setExpandRatio(row, 2);

        Table t = new Table() {
			private static final long serialVersionUID = -8273273624374661640L;

			@Override
            protected String formatPropertyValue(Object rowId, Object colId,
                    Property<?> property) {
                if (colId.equals("Revenue")) {
                    if (property != null && property.getValue() != null) {
                        Double r = (Double) property.getValue();
                        String ret = new DecimalFormat("#.##").format(r);
                        return "$" + ret;
                    } else {
                        return "";
                    }
                }
                return super.formatPropertyValue(rowId, colId, property);
            }
        };
        t.setCaption("Top 10 Titles by Revenue");

        t.setWidth("100%");
        t.setPageLength(0);
        t.addStyleName("plain");
        t.addStyleName("borderless");
        t.setSortEnabled(false);
        t.setColumnAlignment("Revenue", Align.RIGHT);
        t.setRowHeaderMode(RowHeaderMode.INDEX);

        row.addComponent(createPanel(t));
	}

	private void createTopToolBar() {
    	HorizontalLayout top = new HorizontalLayout();
        top.setWidth("100%");
        top.setSpacing(true);
        top.addStyleName("toolbar");
        addComponent(top);
        final Label title = new Label(dashboardTitle);
        title.setSizeUndefined();
        title.addStyleName("h1");
        top.addComponent(title);
        top.setComponentAlignment(title, Alignment.MIDDLE_LEFT);
        top.setExpandRatio(title, 1);
    	
        createToolBarButtons(top);
	}

	private void createToolBarButtons(HorizontalLayout top) {
		Button notify = new Button("2");
        notify.setDescription("Notifications (2 unread)");
        // notify.addStyleName("borderless");
        notify.addStyleName("notifications");
        notify.addStyleName("unread");
        notify.addStyleName("icon-only");
        notify.addStyleName("icon-bell");
        notify.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -3250568494372269492L;

			@Override
            public void buttonClick(ClickEvent event) {
				showNotificationsDialog(event);
            }
        });
        top.addComponent(notify);
        top.setComponentAlignment(notify, Alignment.MIDDLE_LEFT);

        Button edit = new Button();
        edit.addStyleName("icon-edit");
        edit.addStyleName("icon-only");
        top.addComponent(edit);
        edit.setDescription("Edit Dashboard");
        edit.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 6213644941001760982L;

			@Override
            public void buttonClick(ClickEvent event) {
               showEditDialog();
            }
        });
        top.setComponentAlignment(edit, Alignment.MIDDLE_LEFT);
	}

	private CssLayout createPanel(Component content) {
        CssLayout panel = new CssLayout();
        panel.addStyleName("layout-panel");
        panel.setSizeFull();

        Button configure = new Button();
        configure.addStyleName("configure");
        configure.addStyleName("icon-cog");
        configure.addStyleName("icon-only");
        configure.addStyleName("borderless");
        configure.setDescription("Configure");
        configure.addStyleName("small");
        configure.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 8136630148056854773L;

			@Override
            public void buttonClick(ClickEvent event) {
                Notification.show("Not implemented in this demo");
            }
        });
        panel.addComponent(configure);

        panel.addComponent(content);
        return panel;
    }

    @Override
    public void enter(ViewChangeEvent event) {
//        DataProvider dataProvider = ((DashboardUI) getUI()).dataProvider;
        //t.setContainerDataSource(dataProvider.getRevenueByTitle());
    }

    private void showNotificationsPopup(ClickEvent event) {
        notifications = new Window("Notifications");
        VerticalLayout l = new VerticalLayout();
        l.setMargin(true);
        l.setSpacing(true);
        notifications.setContent(l);
        notifications.setWidth("300px");
        notifications.addStyleName("notifications");
        notifications.setClosable(false);
        notifications.setResizable(false);
        notifications.setDraggable(false);
        notifications.setPositionX(event.getClientX() - event.getRelativeX());
        notifications.setPositionY(event.getClientY() - event.getRelativeY());
        notifications.setCloseShortcut(KeyCode.ESCAPE, null);
    }
    
    private void showNotificationsDialog(ClickEvent event) {
		((DashboardUI) getUI()).getSidebarView().clearDashboardButtonBadge();
        event.getButton().removeStyleName("unread");
        event.getButton().setDescription("Notifications");

        if (notifications != null && notifications.getUI() != null)
            notifications.close();
        else {
            showNotificationsPopup(event);
            getUI().addWindow(notifications);
            notifications.focus();
            ((CssLayout) getUI().getContent()).addLayoutClickListener(new LayoutClickListener() {
            	private static final long serialVersionUID = -6067867129998245551L;

				@Override
                public void layoutClick(LayoutClickEvent event) {
					notifications.close();
                    ((CssLayout) getUI().getContent()).removeLayoutClickListener(this);
                }
            });
        }
	}
    
    private void showEditDialog() {
		 final Window w = new Window("Edit Dashboard");

           w.setModal(true);
           w.setClosable(false);
           w.setResizable(false);
           w.addStyleName("edit-dashboard");

           getUI().addWindow(w);

           w.setContent(new VerticalLayout() {
				private static final long serialVersionUID = -4664796188884801858L;
				TextField name = new TextField("Dashboard Name");
               {
                   addComponent(new FormLayout() {
                       {
                           setSizeUndefined();
                           setMargin(true);
                           name.setValue(dashboardTitle);
                           addComponent(name);
                           name.focus();
                           name.selectAll();
                       }
                   });

                   addComponent(new HorizontalLayout() {
                       {
                           setMargin(true);
                           setSpacing(true);
                           addStyleName("footer");
                           setWidth("100%");

                           Button cancel = new Button("Cancel");
                           cancel.addClickListener(new ClickListener() {
                               @Override
                               public void buttonClick(ClickEvent event) {
                                   w.close();
                               }
                           });
                           cancel.setClickShortcut(KeyCode.ESCAPE, null);
                           addComponent(cancel);
                           setExpandRatio(cancel, 1);
                           setComponentAlignment(cancel,
                                   Alignment.TOP_RIGHT);

                           Button ok = new Button("Save");
                           ok.addStyleName("wide");
                           ok.addStyleName("default");
                           ok.addClickListener(new ClickListener() {
                               @Override
                               public void buttonClick(ClickEvent event) {
//                                   title.setValue(dashboardTitle);
                                   w.close();
                               }
                           });
                           ok.setClickShortcut(KeyCode.ENTER, null);
                           addComponent(ok);
                       }
                   });

               }
           });
	}
}
