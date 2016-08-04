package com.gui.input;

import com.gui.interactable.Interactable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInputListener implements MouseListener{
    private Interactable i;
    
    public MouseInputListener(Interactable a){
        i = a;
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        if(i.contains(me,0,0))i.mousePressed(me,0,0);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if(i.contains(me,0,0))i.mouseClicked(me,0,0);
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if(i.contains(me,0,0))i.mouseReleased(me,0,0);
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }

}
