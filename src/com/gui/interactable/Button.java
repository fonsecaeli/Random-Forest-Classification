package com.gui.interactable;

import com.gui.gfx.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Button extends Interactable {
    private String name;
    private int status;
    public static final int XBORDER = 16, YBORDER = 10;//16,10
    public static final int BUTTON_HEIGHT = Font.getCharHeight()+(2*YBORDER);
    public static final int OFF = 0, HOVER = 1, CLICK = 2;
    public static final Color BACKGROUND_COLOR = Color.WHITE,
                              OFF_COLOR = new Color(175, 175, 175), 
                              HOVER_COLOR = new Color(75, 75, 75), 
                              CLICK_COLOR = new Color(77, 144, 254),
                              NULL_COLOR = Color.BLACK;

    public Button(int x, int y, String name){
        this(x, y, Font.getCharWidth()*name.length()+(2*XBORDER), BUTTON_HEIGHT, name);
    }

    private Button(int x, int y, int width, int height, String n){
        super(x, y, width, height);
        name = n;
        status = OFF;
        initImage();
        refreshImage();
    }

    public final String getName(){
        return name;
    }

    public final int getStatus(){
        return status;
    }

    public void refreshStatus(int s){
        status = s;
        refreshImage();
    }

    public void refreshImage(){
        Graphics g = getImage().getGraphics();

        if (getStatus() == OFF)
                g.setColor(OFF_COLOR);
        else if (getStatus() == HOVER)
                g.setColor(HOVER_COLOR);
        else if (getStatus() == CLICK)
                g.setColor(CLICK_COLOR);
        else g.setColor(NULL_COLOR);

        g.drawRect(0, 0, getImage().getWidth()-1, getImage().getHeight()-1);
    }

    protected void initImage(){
        Graphics g = getImage().getGraphics();
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(1, 1, getImage().getWidth()-2, getImage().getHeight()-2);
        BufferedImage text = Font.stringToBufferedImage(name);
        g.drawImage(text, XBORDER, YBORDER, null);
    }

    @Override
    public void mouseDragged(MouseEvent me, int xoff, int yoff){
        refreshStatus(HOVER);
    }

    @Override
    public void mousePressed(MouseEvent me, int xoff, int yoff){
        refreshStatus(CLICK);
    }

    @Override
    public void mouseHovered(MouseEvent me, int xoff, int yoff) {
        refreshStatus(HOVER);
    }

    @Override
    public void mouseNotHovered(MouseEvent me, int xoff, int yoff) {
        refreshStatus(OFF);
    }

    @Override
    public void mouseReleased(MouseEvent me, int xoff, int yoff) {
        if(getStatus()==CLICK)
            onAction(me);
    }

    public void onAction(MouseEvent me){

    }

    public static int getWidth(String name){
        return (Font.stringWidth(name)+2*Button.XBORDER);
    }

    public static int getHeight(String name){
        return (Font.stringHeight(name)+2*Button.YBORDER);
    }
}
