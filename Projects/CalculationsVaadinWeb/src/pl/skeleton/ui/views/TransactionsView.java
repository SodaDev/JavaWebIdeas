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

import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.Trendline;
import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.metadata.TooltipAxes;
import org.dussan.vaadin.dcharts.metadata.XYaxes;
import org.dussan.vaadin.dcharts.metadata.locations.TooltipLocations;
import org.dussan.vaadin.dcharts.metadata.renderers.AxisRenderers;
import org.dussan.vaadin.dcharts.options.Axes;
import org.dussan.vaadin.dcharts.options.Cursor;
import org.dussan.vaadin.dcharts.options.Highlighter;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;
import org.dussan.vaadin.dcharts.renderers.tick.AxisTickRenderer;

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
        
        DataSeries dataSeries = new DataSeries().newSeries().add("23-May-08", 1)
        													.add("24-May-08", 4)
													    	.add("25-May-08", 2)
													    	.add("26-May-08", 6);

	    SeriesDefaults seriesDefaults = new SeriesDefaults().setTrendline(new Trendline().setShow(true));
	
	    Axes axes = new Axes().addAxis(new XYaxis()
							    			.setRenderer(AxisRenderers.DATE)
							    			.setTickOptions(
							    				new AxisTickRenderer()
							    					.setFormatString("%#m/%#d/%y"))
							    					.setNumberTicks(4))
						    	.addAxis(
						    		new XYaxis(XYaxes.Y)
						    			.setTickOptions(
						    				new AxisTickRenderer()
						    					.setFormatString("$%.2f")));
	
	    Highlighter highlighter = new Highlighter()
	    	.setShow(true)
	    	.setSizeAdjust(10)
	    	.setTooltipLocation(TooltipLocations.NORTH)
	    	.setTooltipAxes(TooltipAxes.Y)
	    	.setTooltipFormatString("<b><i><span style='color:red;'>hello</span></i></b> %.2f")
	    	.setUseAxesFormatters(false);
	
	    Cursor cursor = new Cursor()
	    	.setShow(true);
	
	    Options options = new Options()
	    	.addOption(seriesDefaults)
	    	.addOption(axes)
	    	.addOption(highlighter)
	    	.addOption(cursor);
	
	    DCharts chart = new DCharts().setDataSeries(dataSeries).setOptions(options) .show();
	    
	    addComponent(chart);
    }
}
