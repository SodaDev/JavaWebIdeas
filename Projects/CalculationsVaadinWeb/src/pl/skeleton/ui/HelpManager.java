/**
 * DISCLAIMER
 * 
 * The quality of the code is such that you should not copy any of it as best
 * practice how to build Vaadin applications.
 * 
 * @author jouni@vaadin.com
 * 
 */

package pl.skeleton.ui;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

public class HelpManager {
	private static HelpManager instance = new HelpManager();
    private static List<HelpOverlay> overlays = new ArrayList<HelpOverlay>();

    public HelpManager() {}
    
    public static synchronized HelpManager getInstance() {
        return instance;
    }

    public static void closeAll() {
        for (HelpOverlay overlay : overlays) {
            overlay.close();
        }
        overlays.clear();
    }

    public static void createOverlay(String caption, String text, String style) {
        HelpOverlay o = new HelpOverlay();
        o.setCaption(caption);
        Label message = new Label(text, ContentMode.HTML);
        message.setStyleName(style);
        o.addComponent(message);
        o.setStyleName(style);
        overlays.add(o);
        o.center();
        UI.getCurrent().addWindow(o);
    }

}
