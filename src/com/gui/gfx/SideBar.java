package com.gui.gfx;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;

public class SideBar extends Interactable{
	private Button LOAD, SAVEAS;
	private BufferedImage image;
	
	public SideBar(Screen screen){
		this(screen, 100, 0);
	}
	
	public SideBar(Screen screen, int xOffSet, int yOffSet){
		super(xOffSet, yOffSet, screen.getWidth()-xOffSet, screen.getHeight()-yOffSet);
		LOAD = new LoadButton(screen, this);
		SAVEAS = new SaveButton(screen, this);
		initImage(screen);
	}
	
	public void render(Screen screen){
		screen.drawImage(image, getX(), getY());
		LOAD.render(screen);
		SAVEAS.render(screen);
	}
	
	private void initImage(Screen screen){
		image = new BufferedImage(screen.getWidth(), screen.getHeight(), BufferedImage.TYPE_INT_ARGB);
		image.getGraphics().setColor(new Color(175, 175, 175));
		image.getGraphics().fillRect(getX(), getY(), screen.getWidth()-getX(), screen.getHeight()-getY());
		image.getGraphics().setColor(new Color(75, 75, 75));
		image.getGraphics().drawLine(getX(), getY(), getX(), screen.getHeight()-1);
	}
	
	public void onClick(MouseEvent me){
		
	}
	
	public void onHover(MouseEvent me){
		
	}
	
	public void onDrag(MouseEvent me){
		
	}
	
	private class LoadButton extends Button {
		public LoadButton(Screen screen, SideBar sb){
			super((((screen.getWidth()-sb.getX())/2)-(("Load...".length()*Font.getCharWidth()+Button.XBORDER)/2)+sb.getX()), 64, "Load...");
		}
	}
	
	private class SaveButton extends Button {
		public SaveButton(Screen screen, SideBar sb){
			super((((screen.getWidth()-sb.getX())/2)-(("Save As...".length()*Font.getCharWidth()+Button.XBORDER)/2)+sb.getX()), 128, "Save As...");
		}
	}
}
