
package com.gui.gfx;

import java.awt.Color;
import java.awt.Graphics;

public class GUI extends Interactable{
    public static final Color BACKGROUND_COLOR = new Color(240, 240, 240);
    
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
        addInteractable(new SideBar((int)(screen.getWidth()*.8), 0,(int)(screen.getWidth()*.2), screen.getHeight(), screen));
        addInteractable(new Viewer(0, 0,(int)(screen.getWidth()*.8), screen.getHeight(), screen));
    }
}
