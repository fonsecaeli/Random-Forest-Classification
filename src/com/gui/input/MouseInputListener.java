package com.gui.input;

import com.gui.gfx.Interactable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInputListener implements MouseListener{
    private Interactable i;
    
    public MouseInputListener(Interactable a){
        i = a;
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if(i.contains(me))i.mouseClicked(me);
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if(i.contains(me))i.mouseClicked(me);
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }

}
