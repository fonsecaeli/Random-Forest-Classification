package com.gui.gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SideBar extends Interactable{
        public static final Color BACKGROUND_COLOR = new Color(240, 240, 240);
        public static final Color BORDER_COLOR = new Color(75, 75, 75);
	
	public SideBar(int x, int y, int width, int height, Screen screen){
            super(x, y, width, height);
            initImage();
            addInteractable(new LoadButton(getX()+getWidth()/2-Button.getWidth(LoadButton.TITLE), 64));
            addInteractable(new SaveButton(getX()+getWidth()/2-Button.getWidth(SaveButton.TITLE), 128));
	}
	
	private void initImage(){
            Graphics g = getImage().getGraphics();
            g.setColor(BACKGROUND_COLOR);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(BORDER_COLOR);
            g.drawLine(0, 0 , 0, getHeight());
	}
        
	private class LoadButton extends Button {
            private static final String TITLE = "Load...";
            
            public LoadButton(int x, int y){
                    super(x, y, TITLE);
            }

            @Override
            public void onAction(MouseEvent me){
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
            private static final String TITLE = "Save As...";
            
            public SaveButton(int x, int y){
                super(x, y, TITLE);
            }

            /**
            * This, for the moment, uses the open dialog THIS NEEDS TO BE CHANGED TO A SAVE DIALOG
            */
            @Override
            public void onAction(MouseEvent me){
                /*JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(new FileNameExtensionFilter("csv files", "csv"));
                int returnVal = chooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION){
                    String filePath = chooser.getSelectedFile().getPath();
                    if (filePath.substring(filePath.length()-4).equals(".csv")){
                        System.out.println("You chose to open this file: "+filePath);
                        //StaticStorage.newData(chooser.getSelectedFile());
                    }
                }*/
            }

	}
}
