package pl.skeleton.ui;

import java.util.HashMap;
import java.util.Locale;

import pl.skeleton.ui.views.DashboardView;
import pl.skeleton.ui.views.LoginView;
import pl.skeleton.ui.views.MathProblemsView;
import pl.skeleton.ui.views.ReportsView;
import pl.skeleton.ui.views.SalesView;
import pl.skeleton.ui.views.ScheduleView;
import pl.skeleton.ui.views.SidebarView;
import pl.skeleton.ui.views.TransactionsView;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

@Theme("dashboard")
@Title("UI Title")
public class DashboardUI extends UI {
    private static final long serialVersionUID = 1L;

    CssLayout root = new CssLayout();
    LoginView loginLayout;
    SidebarView sidebar;
    CssLayout content = new CssLayout();
    private Navigator nav;
    HashMap<String, Class<? extends View>> routes = createMenuViewsMap();

    @Override
    protected void init(VaadinRequest request) {
        setLocale(Locale.US);

        setContent(root);
        root.addStyleName("root");
        root.setSizeFull();

        // Unfortunate to use an actual widget here, but since CSS generated
        // elements can't be transitioned yet, we must
        Label bg = new Label();
	        bg.setSizeUndefined();
	        bg.addStyleName("login-bg");
	        root.addComponent(bg);

	    buildLoginView(false);
    }

    private HashMap<String, Class<? extends View>> createMenuViewsMap() {
    	HashMap<String, Class<? extends View>> viewsMap = new HashMap<String, Class<? extends View>>();
	    	viewsMap.put(DashboardView.VIEW_NAME, DashboardView.class);
	    	viewsMap.put(SalesView.VIEW_NAME, SalesView.class);
	    	viewsMap.put(TransactionsView.VIEW_NAME, TransactionsView.class);
	    	viewsMap.put(ReportsView.VIEW_NAME, ReportsView.class);
	    	viewsMap.put(ScheduleView.VIEW_NAME, ScheduleView.class);
	    	viewsMap.put(MathProblemsView.VIEW_NAME, MathProblemsView.class);
	    	
    	return viewsMap;
	}

	//========================================= CREATE USER INTERFACE ===================================// 
    public void buildLoginView(boolean exit) {
        if (exit) {
            root.removeAllComponents();
        }
        addStyleName("login");

        loginLayout = new LoginView();
        root.addComponent(loginLayout);
    }

    public void buildMainView() {
    	initializeNavigator();
    	
        removeStyleName("login");
        root.removeComponent(loginLayout);
        root.addComponent(new HorizontalLayout() {
			private static final long serialVersionUID = 635892070650053588L;

			{
                setSizeFull();
                addStyleName("main-view");
                
                //Sidebar
                sidebar = new SidebarView(routes.keySet(), content);
                addComponent(sidebar);
                
                // Content
                addComponent(content);
                content.setSizeFull();
                content.addStyleName("view-content");
                setExpandRatio(content, 1);
            }

        });
    }
    //==================================================================================================//
    
    //=========================================== NAVIGATION ===========================================//
    private void initializeNavigator() {
    	nav = new Navigator(this, content);

        for (String route : routes.keySet()) {
            nav.addView(route, routes.get(route));
        }
        
        fillNavigatorWithSubviews();
        
        nav.addViewChangeListener(new ViewChangeListener() {
			private static final long serialVersionUID = 2858968027595313760L;

			@Override
            public boolean beforeViewChange(ViewChangeEvent event) {
                HelpManager.closeAll();
                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {
//                View newView = event.getNewView();
            }
        });
	}
    //==================================================================================================//

    private void fillNavigatorWithSubviews() {
		// TODO Auto-generated method stub
		
	}

    public SidebarView getSidebarView() {
    	return sidebar;
    }
}
