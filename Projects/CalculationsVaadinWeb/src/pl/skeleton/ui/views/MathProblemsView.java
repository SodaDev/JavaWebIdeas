package pl.skeleton.ui.views;

import java.util.Random;

import org.dussan.vaadin.dcharts.ChartImageFormat;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.DownloadButtonLocation;
import org.dussan.vaadin.dcharts.base.elements.Trendline;
import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.events.chartImageChange.ChartImageChangeEvent;
import org.dussan.vaadin.dcharts.events.chartImageChange.ChartImageChangeHandler;
import org.dussan.vaadin.dcharts.events.click.ChartDataClickEvent;
import org.dussan.vaadin.dcharts.events.click.ChartDataClickHandler;
import org.dussan.vaadin.dcharts.metadata.ConstrainZoomTo;
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
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class MathProblemsView extends VerticalLayout implements View{
	private static final long serialVersionUID = -1197437464122425772L;
	public static final String VIEW_NAME = "MathProblemsView";
	
	@Override
	public void enter(ViewChangeEvent event) {		
        addStyleName("reports");
        buildView();
        setSizeFull();
	}

	private void buildView() {
		addComponent(getChart().show());
//		DCharts controller = getChartController().show();
//		DCharts controller = getExample().show();
//		controller.setHeight(3.0f, Unit.CM);
//		controller.setImmediate(true);
//		addComponent(controller);
	}

	private DCharts getChart() {
//		DataSeries dataSeries = new DataSeries().newSeries().add("23-May-08", 1)
//				.add("24-May-08", 4)
//		    	.add("25-May-08", 2)
//		    	.add("26-May-08", 6);

		DataSeries dataSeries = new DataSeries().newSeries();
		Random rng = new Random();
		for(int i=0; i<1000; i++){
			dataSeries.add(i, rng.nextGaussian());
		}
		
		SeriesDefaults seriesDefaults = new SeriesDefaults().setUseNegativeColors(true).setTrendline(new Trendline().setShow(true));
		
		Axes axes = new Axes().addAxis(new XYaxis()
//		.setRenderer(AxisRenderers.DATE)
		.setAutoscale(true))
//		.setTickOptions(
//			new AxisTickRenderer().setFormatString("%#m/%#d/%y")).setNumberTicks(4))
		.addAxis(new XYaxis(XYaxes.Y)
		.setAutoscale(true));
//		.setTickOptions(
//				new AxisTickRenderer().setFormatString("$%.2f")));
		
		Highlighter highlighter = new Highlighter()
		.setShow(true)
		.setSizeAdjust(10)
		.setTooltipLocation(TooltipLocations.NORTH)
		.setTooltipAxes(TooltipAxes.Y)
		.setTooltipFormatString("<b><i><span style='color:red;'>hello</span></i></b> %.2f")
		.setUseAxesFormatters(false);
		
		Cursor cursor = new Cursor().setShow(true).setZoom(true).setConstrainZoomTo(ConstrainZoomTo.X);
		
		Options options = new Options()
		.setAnimate(true)
		.addOption(seriesDefaults)
		.addOption(axes)
		.addOption(highlighter)
		.addOption(cursor);
		
		return new DCharts().setDataSeries(dataSeries).setOptions(options).setDownloadButtonLocation(DownloadButtonLocation.BOTTOM_RIGHT).setDownloadFilename("AAA").setEnableDownload(true).setChartImageFormat(ChartImageFormat.PNG);
	}
	
	private DCharts getChartController(){
		DataSeries dataSeries = new DataSeries().newSeries().add("23-May-08", 1)
				.add("24-May-08", 4)
		    	.add("25-May-08", 2)
		    	.add("26-May-08", 6).add("29-May-08", 5);

		SeriesDefaults seriesDefaults = new SeriesDefaults().setUseNegativeColors(true).setTrendline(new Trendline().setShow(true));
		
		Axes axes = new Axes().addAxis(new XYaxis()
		.setRenderer(AxisRenderers.DATE)
		.setTickOptions(
			new AxisTickRenderer().setFormatString("%#m/%#d/%y")).setNumberTicks(4))
		.addAxis(new XYaxis(XYaxes.Y)
		.setTickOptions(
				new AxisTickRenderer().setFormatString("$%.2f")));
		
		Highlighter highlighter = new Highlighter()
		.setShow(true)
		.setSizeAdjust(10)
		.setTooltipLocation(TooltipLocations.NORTH)
		.setTooltipAxes(TooltipAxes.Y)
		.setTooltipFormatString("<b><i><span style='color:red;'>hello</span></i></b> %.2f")
		.setUseAxesFormatters(false);
		
		Cursor cursor = new Cursor().setShow(true).setZoom(true).setConstrainZoomTo(ConstrainZoomTo.X).setConstrainOutsideZoom(true);
		
		final DCharts controllerChart = new DCharts();
		
		controllerChart.setEnableChartDataClickEvent(true);
		controllerChart.setEnableChartImageChangeEvent(true);
		controllerChart.setImmediate(true);
		
		controllerChart.addHandler(new ChartDataClickHandler() {
			
			@Override
			public void onChartDataClick(ChartDataClickEvent event) {
				Notification.show("click");
				((DCharts) event.getSource()).setDataSeries(((DCharts) event.getSource()).getDataSeries().add("30-May-08", 5));
				((DCharts) event.getSource()).markAsDirty();
				((DCharts) event.getSource()).replot(true, true);
			}
		});
		
		controllerChart.addHandler(new ChartImageChangeHandler() {
			
			@Override
			public void onChartImageChange(ChartImageChangeEvent event) {
				Notification.show("change");
				event.getChartImage();
			}
		});

		Options options = new Options()
										.setAnimate(true)
										.addOption(seriesDefaults)
										.addOption(axes)
										.addOption(highlighter)
										.addOption(cursor);
		
		controllerChart.setOptions(options);
		controllerChart.setDataSeries(dataSeries);
		
		return controllerChart;
	}
}
