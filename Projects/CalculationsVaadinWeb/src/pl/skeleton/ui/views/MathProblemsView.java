package pl.skeleton.ui.views;

import pl.skeleton.composites.QuadraticEquationComposite;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

public class MathProblemsView extends VerticalLayout implements View{
	private static final long serialVersionUID = -1197437464122425772L;
	public static final String VIEW_NAME = "MathProblemsView";
	
	@Override
	public void enter(ViewChangeEvent event) {
		setSizeFull();
        addStyleName("reports");
        
        addComponent(new QuadraticEquationComposite());
	}
}
