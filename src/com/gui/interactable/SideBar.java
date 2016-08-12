package com.gui.interactable;

import com.main.StaticStorage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.UIManager;

public class SideBar extends Interactable{
    //The background color of the side bar
    public static final Color BACKGROUND_COLOR = new Color(240, 240, 240);
    public static final double VERTICAL_SPACING_PROPORTION = 0.2;

    /**
     * Initializes the sidebar with the given dimensions
     */
    public SideBar(int x, int y, int width, int height){
        super(x, y, width, height);
        initImage();
        
        int sum=(int)(VERTICAL_SPACING_PROPORTION*getHeight());
        addInteractable(new LoadButton(getWidth()/2-Button.getWidth(LoadButton.TITLE)/2, sum));
        sum+=(int)(VERTICAL_SPACING_PROPORTION*getHeight());
        addInteractable(new RefreshForestButton(getWidth()/2-Button.getWidth(RefreshForestButton.TITLE)/2, sum));
    }

    /**
     * Initializes the image
     */
    private void initImage(){
        Graphics g = getImage().getGraphics();
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(GUI.BORDER_COLOR);
        g.drawLine(0, 0 , 0, getHeight());
    }

    /**
     * The button which, when clicked, brings up the load menu
     */
    private class LoadButton extends Button {
        //The title of the button
        private static final String TITLE = "Load...";

        //Default constructor, only needs the location as title is stored and width/height is handled by the super
        public LoadButton(int x, int y){
                super(x, y, TITLE);
        }

        //Brings up the load window for importing data
        @Override
        public void onAction(MouseEvent me){
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (Exception ex) {ex.printStackTrace();}
                JFileChooser chooser = new JFileChooser();
		chooser.setPreferredSize(new Dimension(960, 640));
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

    /**
     * The button which, when clicked, refreshes the current forest
     */
    private class RefreshForestButton extends Button {
        //The title of the button
        private static final String TITLE = "Refresh Forest";

        //Default constructor, only needs the location as title is stored and width/height is handled by the super
        public RefreshForestButton(int x, int y){
            super(x, y, TITLE);
        }

        //brings up the save menu
        @Override
        public void onAction(MouseEvent me){
            StaticStorage.refreshCurrentForest();
        }

    }
}
