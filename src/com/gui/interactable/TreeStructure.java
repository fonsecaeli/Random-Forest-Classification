
package com.gui.interactable;

import com.gui.gfx.Font;
import com.gui.gfx.Screen;
import static com.gui.interactable.Viewer.BACKGROUND_COLOR;
import com.main.DataSet;
import com.main.StaticStorage;
import java.awt.Color;
import java.awt.Graphics;

public class TreeStructure extends Interactable{
    private DataSet dataSet;

    public TreeStructure(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    public void setDataSet(DataSet ds){
        dataSet = ds;
    }
    
    public void render(int xoff, int yoff, Screen screen){
        refreshImage();
        super.render(xoff, yoff, screen);
    }
    
    
    public void refreshImage(){
        if(StaticStorage.getCurrentDataSet()!=null && StaticStorage.getCurrentDataSet()!=dataSet){
            dataSet=StaticStorage.getCurrentDataSet();
            Graphics g = getImage().getGraphics();
            g.setColor(BACKGROUND_COLOR);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(Font.stringToBufferedImage(StaticStorage.getCurrentRandomForest().toString()), 0, 0, null);
        }
    }
    
}
