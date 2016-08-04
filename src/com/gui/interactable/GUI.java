
package com.gui.interactable;

import com.gui.gfx.Screen;
import java.awt.Color;
import java.awt.Graphics;

public class GUI extends Interactable{
    public static final Color BACKGROUND_COLOR = new Color(240, 240, 240);
    public static final double VIEWER_WIDTH_PROPORTION = 0.8;
    
    public GUI(Screen screen){
        super(0, 0, screen.getWidth(), screen.getHeight());
        initImage();
        init(screen);
    }
    
    private void initImage(){
        Graphics g = getImage().getGraphics();
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
    
    private final void init(Screen screen){
        addInteractable(new SideBar((int)(screen.getWidth()*VIEWER_WIDTH_PROPORTION), 0,(int)(screen.getWidth()*(1-VIEWER_WIDTH_PROPORTION)), screen.getHeight(), screen));
        addInteractable(new Viewer(0, 0,(int)(screen.getWidth()*VIEWER_WIDTH_PROPORTION), screen.getHeight(), screen));
    }
}
