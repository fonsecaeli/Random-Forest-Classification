package com.gui.interactable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.swing.JFileChooser;
import java.swing.filechooser.FileNameExtensionFilter;
import com.gui.gfx.Screen;
import com.main.StaticStorage;


public class Query extends Interactable {
	private static final Color BACKGROUND_COLOR = Color.WHITE;
	
	public Query(int x, int y, int width, int height){
		super(x, y, width, height);
		initImage();
		addInteractable(new QueryButton(0, 0));
	}
	
	private void initImage(){
		Graphics g = getImage().getGraphics();
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	public class QueryButton extends Button {
		private Query dad;
		
		public QueryButton(int x, int y, Query dad){
			super(x, y, "Query...");
			this.dad = dad;
		}
		
		@Override
		public void onAction(MouseEvent me){
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new FileNameExtensionFilter("csv files", "csv"));
			int returnVal = chooser.showOpenDialog(null);
			System.out.println(returnVal);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				String filePath = chooser.getSelectedFile.getPath();
				if (filePath.substring(filePath.length()-4).equals(".csv")){
					System.out.println("You chose to open this file: "+filePath);
					
				}
			}
		}
	}
}
