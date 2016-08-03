package com.gui.gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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
		g.setColor(new Color(75, 75, 75));
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(new Color(75, 75, 75));
		g.drawLine(getX(), getY(), getX(), getHeight());
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
				if (filePath.substring(filePath.length()-4, filePath.length()).equals(".csv")){
					System.out.println("You chose to open this file: "+filePath);
					//StaticStorage.newData(chooser.getSelectedFile().getName());
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
				if (filePath.substring(filePath.length()-4, filePath.length()).equals(".csv")){
					System.out.println("You chose to open this file: "+filePath);
					//StaticStorage.newData(chooser.getSelectedFile().getName());
				}
			}
		}

	}
}
