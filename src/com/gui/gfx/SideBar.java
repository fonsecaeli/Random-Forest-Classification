package com.gui.gfx;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class SideBar {
	private Button LOAD, SAVEAS;
	private BufferedImage image;
	private int xOffSet, yOffSet = 0;
	
	public SideBar(Screen screen){
		LOAD = new LoadButton(screen);
		SAVEAS = new SaveButton(screen);
		initImage(screen);
	}
	
	public int getXOffSet(){
		return xOffSet;
	}
	
	public int getYOffSet(){
		return yOffSet;
	}
	
	public void render(Screen screen){
		screen.drawImage(image, xOffSet, yOffSet);
		LOAD.render(screen);
		SAVEAS.render(screen);
	}
	
	private void initImage(Screen screen){
		image = new BufferedImage(screen.getWidth(), screen.getHeight(), BufferedImage.TYPE_INT_ARGB);
		image.getGraphics().setColor(new Color(175, 175, 175));
		image.getGraphics().fillRect(xOffSet, yOffSet, screen.getWidth()-xOffSet, screen.getHeight()-yOffSet);
		image.getGraphics().setColor(new Color(75, 75, 75));
		image.getGraphics().drawLine(xOffSet, yOffSet, xOffSet, screen.getHeight()-1);
	}
	
	private class LoadButton extends Button {
		public LoadButton(Screen screen){
			super((((screen.getWidth()-getXOffSet())/2)-(("Load...".length()*Font.getCharWidth()+Button.XBORDER)/2)+getXOffSet()), 64, "Load...");
		}
	}
	
	private class SaveButton extends Button {
		public SaveButton(Screen screen){
			super((((screen.getWidth()-getXOffSet())/2)-(("Save As...".length()*Font.getCharWidth()+Button.XBORDER)/2)+getXOffSet()), 128, "Save As...");
		}
	}
}
