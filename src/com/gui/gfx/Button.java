package com.gui.gfx;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Button extends Interactable {
	private String name;
	public static final int XBORDER = 2, YBORDER = 2;
	public static final int OFF = 0, HOVER = 1, CLICK = 2;
	
	public Button(int x, int y, String name){
            this(x, y, Font.getCharWidth()*name.length()+(2*XBORDER), Font.getCharHeight()+(2*YBORDER), name);
	}
	
	private Button(int x, int y, int width, int height, String name){
            super(x, y, width, height);
            this.name = name;
            initImage();
            refreshImage(OFF);
	}
	
	public final String getName(){
            return name;
	}
	
	public void refreshImage(int status){
            Graphics g = getImage().getGraphics();

            if (status == OFF)
                    g.setColor(new Color(175, 175, 175));
            else if (status == HOVER)
                    g.setColor(new Color(75, 75, 75));
            else if (status == CLICK)
                    g.setColor(new Color(77, 144, 254));
            else g.setColor(Color.BLACK);

            g.drawRect(0, 0, getImage().getWidth()-1, getImage().getHeight()-1);
            g.setColor(new Color(255, 255, 255));
            g.drawRect(1, 1, getImage().getWidth()-2, getImage().getHeight()-2);
	}
	
	private void initImage(){
            BufferedImage text = Font.stringToBufferedImage(name);
            getImage().getGraphics().drawImage(text, XBORDER, YBORDER, null);
	}
	
        @Override
	public void mouseClicked(MouseEvent me){
            refreshImage(CLICK);
	}

        @Override
        public void mouseMoved(MouseEvent me) {
            refreshImage(HOVER);
        }
}
