package com.gui.gfx;

import com.main.DataSet;
import com.main.StaticStorage;
import java.awt.Color;
import java.awt.Graphics;

public class Viewer extends Interactable{
        private DataSet dataSet;
        public static final Color BACKGROUND_COLOR = Color.WHITE;
    
	public Viewer(int x, int y, int width, int height, Screen screen){
		super(x, y, width, height);
		initImage();
                
                dataSet = StaticStorage.getCurrentDataSet();
                
	}
        
	private void initImage(){
                Graphics g = getImage().getGraphics();
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
                refreshImage();
	}
        
        public void refreshImage(){
            if(StaticStorage.getCurrentDataSet()!=null && StaticStorage.getCurrentDataSet()!=dataSet){
                dataSet=StaticStorage.getCurrentDataSet();
                Graphics g = getImage().getGraphics();
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
                g.drawImage(Font.stringToBufferedImage(StaticStorage.getCurrentRandomForest().toString()), getX(), getY(), null);
            }
	}
        
        @Override
        public void render(Screen screen){
            refreshImage();
            super.render(screen);
        }
}
