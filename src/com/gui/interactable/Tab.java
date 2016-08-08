package com.gui.interactable;


import com.gui.gfx.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


public class Tab extends Button{
    public static final Color SELECTED_COLOR = Color.WHITE,
                              UNSELECTED_COLOR = new Color(240, 240, 240);

    private TabSystem tabSys; //for finding which is selected

    public Tab(int x, int y, String name, TabSystem ts) {
        super(x, y, name);
        tabSys = ts;
        initImage();
        refreshImage();

    }

    @Override
    public void refreshImage(){
        if(tabSys!=null){
            Graphics g = getImage().getGraphics();

            if(isSelectedTab()){

                g.setColor(HOVER_COLOR);
                g.drawRect(0, 0, getWidth()-1, getHeight());


                g.setColor(SELECTED_COLOR);
                g.fillRect(1, 1, getWidth()-2, getHeight()-1);
                BufferedImage text = Font.stringToBufferedImage(getName());
                g.drawImage(text, XBORDER, YBORDER, null);

            } else {

                if (getStatus() == OFF)
                        g.setColor(OFF_COLOR);
                else if (getStatus() == HOVER || isSelectedTab())
                        g.setColor(HOVER_COLOR);
                else if (getStatus() == CLICK)
                        g.setColor(CLICK_COLOR);
                else g.setColor(NULL_COLOR);

                g.drawRect(0, 0, getWidth()-1, getHeight()-1);
                g.setColor(GUI.BORDER_COLOR);
                g.drawLine(0, getHeight()-1, getWidth()-1, getHeight()-1);

                g.setColor(UNSELECTED_COLOR);
                g.fillRect(1, 1, getImage().getWidth()-2, getImage().getHeight()-2);
                BufferedImage text = Font.stringToBufferedImage(getName());
                g.drawImage(text, XBORDER, YBORDER, null);
            }
        }
    }

    public boolean isSelectedTab(){
        return tabSys.getSelectedTab() == this;
    }

    protected void initImage(){
        Graphics g = getImage().getGraphics();
        g.setColor(Button.BACKGROUND_COLOR);
        g.fillRect(1, 1, getImage().getWidth()-2, getImage().getHeight()-2);
        BufferedImage text = Font.stringToBufferedImage(getName());
        g.drawImage(text, XBORDER, YBORDER, null);
    }

    @Override
    public void onAction(MouseEvent me){
        tabSys.setSelectedTab(this);
        tabSys.refreshImage();
    }

    
    public String toString(){
        return getName();
    }
}