package com.gui.input;

import com.gui.interactable.Interactable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionInputListener implements MouseMotionListener{
    private Interactable i;
    
    public MouseMotionInputListener(Interactable a){
        i = a;
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if(i.contains(me,0,0))i.mouseDragged(me,0,0);
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        if(i.contains(me,0,0))i.mouseHovered(me,0,0);
    }
    
}
