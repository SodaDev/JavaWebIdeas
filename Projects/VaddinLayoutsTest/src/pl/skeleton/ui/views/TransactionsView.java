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

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

public class TransactionsView extends VerticalLayout implements View {
	public static final String VIEW_NAME = "TransactionsView";
    private static final long serialVersionUID = 1L;

    @Override
    public void enter(ViewChangeEvent event) {
        setSizeFull();
        addStyleName("transactions");
    }
}
