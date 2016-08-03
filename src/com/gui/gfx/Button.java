package com.gui.gfx;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Button extends Interactable {
	private String name;
        private int status;
	public static final int XBORDER = 6, YBORDER = 6;
	public static final int OFF = 0, HOVER = 1, CLICK = 2;
        public static final Color OFF_COLOR = new Color(175, 175, 175), 
                                  HOVER_COLOR = new Color(75, 75, 75), 
                                  CLICK_COLOR = new Color(77, 144, 254),
                                  NULL_COLOR = Color.BLACK;
	
	public Button(int x, int y, String name){
            this(x, y, Font.getCharWidth()*name.length()+(2*XBORDER), Font.getCharHeight()+(2*YBORDER), name);
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
	
	private void initImage(){
            Graphics g = getImage().getGraphics();
            g.setColor(new Color(255, 255, 255));
            g.fillRect(1, 1, getImage().getWidth()-2, getImage().getHeight()-2);
            BufferedImage text = Font.stringToBufferedImage(name);
            g.drawImage(text, XBORDER, YBORDER, null);
	}
	
        @Override
	public void mouseDragged(MouseEvent me){
            refreshStatus(HOVER);
	}
        
        @Override
	public void mousePressed(MouseEvent me){
            refreshStatus(CLICK);
	}
        
        @Override
        public void mouseHovered(MouseEvent me) {
            refreshStatus(HOVER);
        }
        
        @Override
        public void mouseNotHovered(MouseEvent me) {
            refreshStatus(OFF);
        }
        
        @Override
        public void mouseReleased(MouseEvent me) {
            if(getStatus()==CLICK)
                onAction(me);
        }
        
        public void onAction(MouseEvent me){
            
        }
}
