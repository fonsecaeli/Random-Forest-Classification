
package com.gui.gfx;

public class GUI extends Interactable{
    
    public GUI(Screen screen){
        super(0, 0, screen.getWidth(), screen.getHeight());
        init(screen);
    }
    
    private final void init(Screen screen){
        addInteractable(new SideBar((int)(screen.getWidth()*.8), 0,(int)(screen.getWidth()*.2), screen.getHeight(), screen));
    }
}
