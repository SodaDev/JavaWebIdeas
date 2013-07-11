package pl.skeleton.ui.views;

import java.util.HashMap;
import java.util.Set;

import pl.skeleton.ui.DashboardUI;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class SidebarView extends VerticalLayout {
	private static final long serialVersionUID = 50979943946637950L;
    private CssLayout menu = new CssLayout();
	private VerticalLayout userMenu = new VerticalLayout();
	private Navigator navigator;
    HashMap<String, Button> viewNameToMenuButton = new HashMap<String, Button>();
	
	public SidebarView(Set<String> set, CssLayout content) {
		this.navigator = UI.getCurrent().getNavigator();
		addStyleName("sidebar");
		setWidth(null);
		setHeight("100%");

		// Branding element
		addComponent(getBrandingElement());

		// Main menu
		addComponent(menu);
		setExpandRatio(menu, 1);
        menu.removeAllComponents();
        
		// User menu
		createUserMenu();
		
		createPageButtons(set, content);
	}
	//============================================ CREATE UI  ==========================================//
	private Component getBrandingElement() {
		CssLayout brandingElement = new CssLayout();
			brandingElement.addStyleName("branding");
			Label logo = new Label("<span>Calculations</span> Website", ContentMode.HTML);
				logo.setSizeUndefined();
			brandingElement.addComponent(logo);
				
		return brandingElement;
	}
	
	private void createPageButtons(Set<String> set, CssLayout content) {
        for (final String view : set) {
            createPageButtonForMenu(view);
        }
        
        menu.addStyleName("menu");
        menu.setHeight("100%");

        viewNameToMenuButton.get(DashboardView.VIEW_NAME).setHtmlContentAllowed(true);
        viewNameToMenuButton.get(DashboardView.VIEW_NAME).setCaption("Dashboard<span class=\"badge\">2</span>"); //automaticly set badge for demo

        initializeNavigation();
        
	}

	private void createPageButtonForMenu(final String view) {
		Button buttonPage = new NativeButton(view.substring(0, 1).toUpperCase() + view.substring(1).replace('-', ' '));
        buttonPage.addStyleName("icon-" + view); //fontello.scss bottom
        buttonPage.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 7236320713225151596L;

			@Override
            public void buttonClick(ClickEvent event) {
                clearMenuSelection();
                event.getButton().addStyleName("selected");
                if (!navigator.getState().equals(view))
                    navigator.navigateTo(view);
            }
        });

        menu.addComponent(buttonPage);
        viewNameToMenuButton.put(view, buttonPage);
	}
	
	private void createUserMenu() {
		userMenu = new VerticalLayout();
		userMenu.setSizeUndefined();
		userMenu.addStyleName("user");
		Image profilePic = new Image(null, new ThemeResource("img/profile-pic.png"));
			profilePic.setWidth("34px");
		userMenu.addComponent(profilePic);
				
		Label userName = new Label("NAME SURNAME");
		userName.setSizeUndefined();
		userMenu.addComponent(userName);
		
		createMenuBar(userMenu);
		this.addComponent(userMenu);
	}

	private void createMenuBar(VerticalLayout menuLayout) {
		Command menuAction = new Command() {
			private static final long serialVersionUID = 4743044714216490733L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				Notification.show("Not implemented yet");
			}
		};
		
		MenuBar userMenu = new MenuBar();
		MenuItem settingsMenu = userMenu.addItem("", null);
			settingsMenu.setStyleName("icon-cog");
			settingsMenu.addItem("Settings", menuAction);
			settingsMenu.addItem("Preferences", menuAction);
			settingsMenu.addSeparator();
			settingsMenu.addItem("My Account", menuAction);
		menuLayout.addComponent(userMenu);

		Button exit = new NativeButton("Exit");
			exit.addStyleName("icon-cancel");
			exit.setDescription("Sign Out");
		exit.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -6495519416387469755L;

			@Override
			public void buttonClick(ClickEvent event) {
				((DashboardUI)UI.getCurrent()).buildLoginView(true);
			}
		});
		menuLayout.addComponent(exit);
	}
	
	//==================================================================================================//
	
	//======================================== UI INTERACTIONS =========================================//
    void clearDashboardButtonBadge() {
        viewNameToMenuButton.get(DashboardView.VIEW_NAME).setCaption("Dashboard");
    }
    
    void updateReportsButtonBadge(String badgeCount) {
        viewNameToMenuButton.get(ReportsView.VIEW_NAME).setHtmlContentAllowed(true);
        viewNameToMenuButton.get(ReportsView.VIEW_NAME).setCaption("Reports<span class=\"badge\">" + badgeCount + "</span>");
    }
    
    public void clearMenuSelection() {
		for(int componentId = 0; componentId<menu.getComponentCount(); componentId++) {
			Component component = menu.getComponent(componentId);
            if (component instanceof NativeButton) {
                component.removeStyleName("selected");
            } 
		}
    }
    //===================================================================================================//
    
	private void initializeNavigation() {
		String f = Page.getCurrent().getUriFragment();
        if (f != null && f.startsWith("!")) {
            f = f.substring(1);
        }
        if (f == null || f.equals("")) {
            navigator.navigateTo(DashboardView.VIEW_NAME);
            getComponent(0).addStyleName("selected");
        } else {
            navigator.navigateTo(f);
            viewNameToMenuButton.get(f).addStyleName("selected");
        }
	}
}
