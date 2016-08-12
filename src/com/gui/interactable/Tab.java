package com.gui.interactable;


import com.gui.gfx.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


public class Tab extends Button{
    //The background colors for if a button is selected or unselected
    public static final Color SELECTED_COLOR = Color.WHITE,
                              UNSELECTED_COLOR = new Color(240, 240, 240);

    //stores the parent TabSystem, as it needs to access/modify the selected tab
    private TabSystem tabSys; //for finding which is selected

    /**
     * The default constructor, passes params to super and then inits
     * @param x the relative x coord of the tab
     * @param y the relative y coord of the tab
     * @param name the tab that appears on the tab
     * @param ts the parent TabSystem
     */
    public Tab(int x, int y, String name, TabSystem ts) {
        super(x, y, name);
        tabSys = ts;
        initImage();
        refreshImage();

    }

    //Combines the refresh behavior for buttons and whether the tab is selected
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

    /**
     * @return whether this tab is the selected tab
     */
    public boolean isSelectedTab(){
        return tabSys.getSelectedTab() == this;
    }

    /**
     * creates the image
     */
    protected void initImage(){
        Graphics g = getImage().getGraphics();
        g.setColor(Button.BACKGROUND_COLOR);
        g.fillRect(1, 1, getImage().getWidth()-2, getImage().getHeight()-2);
        BufferedImage text = Font.stringToBufferedImage(getName());
        g.drawImage(text, XBORDER, YBORDER, null);
    }

    /**
     * When the tab is clicked on, set it as the selected tab and refresh the images of all tabs
     */
    @Override
    public void onAction(MouseEvent me){
        tabSys.setSelectedTab(this);
        tabSys.refreshImage();
    }

    
    @Override
    public String toString(){
        return getName();
    }
}