package com.codename1.demo.mapanimate.titleoverlay;


import com.codename1.googlemaps.MapContainer;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import java.io.IOException;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class AnimateTitleOverlay {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        //Toolbar.setGlobalToolbar(true);

        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
        Form m = new Form("Map", new BorderLayout());
        Toolbar tb = new Toolbar(true);
        m.setToolbar(tb);
        m.add(BorderLayout.CENTER, new MapContainer("***IMPORTANT: SET JS KEY HERE****"));
        
        Label title = new Label("Map", "Title");
        ButtonGroup bg = new ButtonGroup();
        
        RadioButton modeA = RadioButton.createToggle("Mode A", bg);
        RadioButton modeB = RadioButton.createToggle("Mode B", bg);
        RadioButton modeC = RadioButton.createToggle("Mode C", bg);
        modeA.setUIID("SmallTitle");
        modeB.setUIID("SmallTitle");
        modeC.setUIID("SmallTitle");
        modeA.setSelected(true);
        Container radioGrid = GridLayout.encloseIn(3, modeA, modeB, modeC);

        Label whiteLine = new Label();
        whiteLine.setShowEvenIfBlank(true);
        whiteLine.getUnselectedStyle().setBgColor(0xffffff);
        whiteLine.getUnselectedStyle().setBgTransparency(255);
        whiteLine.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_DIPS);
        whiteLine.getUnselectedStyle().setPadding(1, 0, 1, 1);
        Container lineGrid = GridLayout.encloseIn(3, whiteLine, new Label(), new Label());
        bg.addActionListener(e -> {
            int offset = radioGrid.getComponentIndex(e.getComponent());
            whiteLine.remove();
            lineGrid.addComponent(offset, whiteLine);
            lineGrid.animateLayout(150);
        });
        
        
        Container titleArea = BoxLayout.encloseY(title, radioGrid, lineGrid);
        tb.setTitleComponent(titleArea);
        
        
        m.show();
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = Display.getInstance().getCurrent();
        }
    }
    
    public void destroy() {
    }

}
