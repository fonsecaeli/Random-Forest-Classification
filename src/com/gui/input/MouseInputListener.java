package com.gui.input;

import com.gui.gfx.GUI;
import com.gui.gfx.Interactable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class MouseInputListener implements MouseListener{
    private GUI gui;
    
    public MouseInputListener(GUI g){
        gui = g;
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if(gui.contains(me))gui.mouseClicked(me);
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if(gui.contains(me))gui.mouseClicked(me);
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }

}
