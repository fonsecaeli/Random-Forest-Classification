package com.gui.input;

import com.gui.gfx.Interactable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionInputListener implements MouseMotionListener{
    private Interactable i;
    
    public MouseMotionInputListener(Interactable a){
        i = a;
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if(i.contains(me))i.mouseDragged(me);
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        if(i.contains(me))i.mouseHovered(me);
    }
    
}
