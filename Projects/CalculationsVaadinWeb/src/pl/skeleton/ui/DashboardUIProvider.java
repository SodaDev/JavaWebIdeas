package pl.skeleton.ui;

import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.ui.UI;

public class DashboardUIProvider extends UIProvider {
	private static final long serialVersionUID = 195206685008840667L;

	@Override
    public Class<? extends UI> getUIClass(UIClassSelectionEvent event) {
        if (event.getRequest().getParameter("mobile") != null
                && event.getRequest().getParameter("mobile").equals("false")) {
            return DashboardUI.class;
        }

        String lowerCaseHeader = event.getRequest().getHeader("user-agent").toLowerCase();
        if (lowerCaseHeader.contains("mobile") && !lowerCaseHeader.contains("ipad")) {
            return MobileCheckUI.class;
        }

        return DashboardUI.class;
    }
}