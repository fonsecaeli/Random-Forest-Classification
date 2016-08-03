package com.gui.gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SideBar extends Interactable{
        public static final Color BACKGROUND_COLOR = new Color(175, 175, 175);
        public static final Color BORDER_COLOR = new Color(75, 75, 75);
	
	public SideBar(int x, int y, int width, int height, Screen screen){
            super(x, y, width, height);
            initImage();
            addInteractable(new LoadButton(screen, this));
            //addInteractable(new SaveButton(screen, this));
	}
	
	private void initImage(){
            Graphics g = getImage().getGraphics();
            g.setColor(BACKGROUND_COLOR);
            g.fillRect(getX(), getY(), getWidth(), getHeight());
            g.setColor(BORDER_COLOR);
            g.drawLine(getX(), getY(), getX()+1, getHeight());
	}
        
	private class LoadButton extends Button {
		public LoadButton(Screen screen, SideBar sb){
			super((((screen.getWidth()-sb.getX())/2)-(("Load...".length()*Font.getCharWidth()+Button.XBORDER)/2)+sb.getX()), 64, "Load...");
		}
		
		public void mouseClicked(MouseEvent me){
			super.mouseClicked(me);
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new FileNameExtensionFilter("csv files", "csv"));
			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				String filePath = chooser.getSelectedFile().getPath();
				if (filePath.substring(filePath.length()-4).equals(".csv")){
					System.out.println("You chose to open this file: "+filePath);
					com.main.StaticStorage.newData(chooser.getSelectedFile());
				}
			}
		}
	}
	
	private class SaveButton extends Button {
		public SaveButton(Screen screen, SideBar sb){
			super((((screen.getWidth()-sb.getX())/2)-(("Save As...".length()*Font.getCharWidth()+Button.XBORDER)/2)+sb.getX()), 128, "Save As...");
		}
		
		/**
		* This, for the moment, uses the open dialog THIS NEEDS TO BE CHANGED TO A SAVE DIALOG
		*/
		
		public void mouseClicked(MouseEvent me){
			super.mouseClicked(me);
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new FileNameExtensionFilter("csv files", "csv"));
			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				String filePath = chooser.getSelectedFile().getPath();
				if (filePath.substring(filePath.length()-4).equals(".csv")){
					System.out.println("You chose to open this file: "+filePath);
					//StaticStorage.newData(chooser.getSelectedFile());
				}
			}
		}

	}
}
