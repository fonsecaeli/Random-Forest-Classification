package com.gui.gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class SideBar extends Interactable{
	
	private SideBar(Screen screen){
		this(100, 0, 0,0,screen);
	}
	
	public SideBar(int x, int y, int width, int height, Screen screen){
		super(x, y, screen.getWidth()-x, screen.getHeight()-y);
		initImage(screen);
                addInteractable(new LoadButton(screen, this));
                addInteractable(new SaveButton(screen, this));
	}
	
	private void initImage(Screen screen){
                Graphics g = getImage().getGraphics();
		g.setColor(new Color(175, 175, 175));
		g.fillRect(getX(), getY(), screen.getWidth()-getX(), screen.getHeight()-getY());
		g.setColor(new Color(75, 75, 75));
		g.drawLine(getX(), getY(), getX(), screen.getHeight()-1);
	}
	
	private class LoadButton extends Button {
		public LoadButton(Screen screen, SideBar sb){
			super((((screen.getWidth()-sb.getX())/2)-(("Load...".length()*Font.getCharWidth()+Button.XBORDER)/2)+sb.getX()), 64, "Load...");
		}
		
		public void mouseClicked(MouseEvent me){
			super.mouseClicked(me);
			System.out.println("success!");
			
		}
	}
	
	private class SaveButton extends Button {
		public SaveButton(Screen screen, SideBar sb){
			super((((screen.getWidth()-sb.getX())/2)-(("Save As...".length()*Font.getCharWidth()+Button.XBORDER)/2)+sb.getX()), 128, "Save As...");
		}
	}
}
