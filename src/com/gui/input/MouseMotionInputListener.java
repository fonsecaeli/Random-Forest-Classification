package com.gui.input;

import com.gui.gfx.Interactable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class MouseMotionInputListener implements MouseMotionListener{
    private List<Interactable> listeners = new ArrayList<>();

    @Override
    public void mouseDragged(MouseEvent me) {
        for(Interactable i : listeners){
            if(i.contains(me))
                i.mouseDragged(me);
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        for(Interactable i : listeners){
            if(i.contains(me))
                i.mouseMoved(me);
        }
    }
    
}
