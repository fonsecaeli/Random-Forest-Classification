package com.gui.input;

import com.gui.gfx.GUI;
import com.gui.gfx.Interactable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class MouseMotionInputListener implements MouseMotionListener{
    private GUI gui;
    
    public MouseMotionInputListener(GUI g){
        gui = g;
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if(gui.contains(me))gui.mouseDragged(me);
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        if(gui.contains(me))gui.mouseMoved(me);
    }
    
}
