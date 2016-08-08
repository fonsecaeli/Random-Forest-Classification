
package com.gui.interactable;

import com.gui.gfx.Screen;
import java.awt.Color;
import java.awt.Graphics;

/**
 * The main GUI class, contains all other interactables
 * This is what is called when the screen renders
 */
public class GUI extends Interactable{
    public static final Color BACKGROUND_COLOR = Color.WHITE,
                              BORDER_COLOR = new Color(75, 75, 75);
    public static final double VIEWER_WIDTH_PROPORTION = 0.8;
    
    public GUI(Screen screen){
        super(0, 0, screen.getWidth(), screen.getHeight());
        initImage();
        init(screen);
    }
    
    /**
     * draws a background with the specified color
     */
    private void initImage(){
        Graphics g = getImage().getGraphics();
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
    
    /**
     * Initalizes the interactables it has
     */
    private final void init(Screen screen){
        addInteractable(new Viewer(0,
                                   0,
                                   (int)(screen.getWidth()*VIEWER_WIDTH_PROPORTION), 
                                   screen.getHeight()));
        addInteractable(new SideBar((int)(screen.getWidth()*VIEWER_WIDTH_PROPORTION), 
                                          0,
                                          (int)(screen.getWidth()*(1-VIEWER_WIDTH_PROPORTION)), 
                                          screen.getHeight()));
    }
}
