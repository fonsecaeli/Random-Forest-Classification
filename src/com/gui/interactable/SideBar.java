package com.gui.interactable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SideBar extends Interactable{
    public static final Color BACKGROUND_COLOR = new Color(240, 240, 240);

    /**
     * Initializes the sidebar with the given dimensions
     */
    public SideBar(int x, int y, int width, int height){
        super(x, y, width, height);
        initImage();
        addInteractable(new LoadButton(getWidth()/2-Button.getWidth(LoadButton.TITLE)/2, 64));
        addInteractable(new SaveButton(getWidth()/2-Button.getWidth(SaveButton.TITLE)/2, 128));
    }

    private void initImage(){
        Graphics g = getImage().getGraphics();
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(GUI.BORDER_COLOR);
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

        @Override
        public void onAction(MouseEvent me){
            
        }

    }
}
