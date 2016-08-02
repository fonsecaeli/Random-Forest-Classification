package com.gui.input;

import com.gui.gfx.Interactable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class MouseInputListener implements MouseListener{
    private List<Interactable> listeners = new ArrayList<>();
    
    public void addListener(Interactable i){
        listeners.add(i);
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        for(Interactable i : listeners){
            if(i.contains(me))
                i.mouseClicked(me);
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        for(Interactable i : listeners){
            if(i.contains(me))
                i.mouseReleased(me);
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }

}
