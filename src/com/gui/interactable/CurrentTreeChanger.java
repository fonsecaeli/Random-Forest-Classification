
package com.gui.interactable;

import com.gui.gfx.Font;
import com.gui.gfx.Screen;
import com.main.DecisionTree;
import com.main.StaticStorage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class CurrentTreeChanger extends Interactable {
    public static final Color BACKGROUND_COLOR = Color.WHITE;
    public static final int BUTTON_VERTICAL_SPACING = 6;
    public static final int BUTTON_HORIZONTAL_SPACING = 6;
    
    private DecisionTree tree;
    private Button left, right;

    public CurrentTreeChanger(int x1, int y1, int w) {
        super(x1, y1, w, Button.BUTTON_HEIGHT+2*BUTTON_VERTICAL_SPACING);
        init();
    }
    
    /**
     * Init the buttons, as well as adding them
     */
    private void init(){
        left = new LeftButton(BUTTON_HORIZONTAL_SPACING,
                                BUTTON_VERTICAL_SPACING);
        right = new RightButton(getWidth()-Button.getWidth(">")-BUTTON_HORIZONTAL_SPACING,
                              BUTTON_VERTICAL_SPACING);
        addInteractable(left);
        addInteractable(right);
        initImage();
    }
    
    /**
     * Init the image
     */
    private void initImage(){
        Graphics g = getImage().getGraphics();
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
            
        g.setColor(GUI.BORDER_COLOR);
        g.drawLine(0, getHeight()-1, getWidth(), getHeight()-1);
    }
    
    
    //redraw the rect, draw text for the current tree if it exists
    private void refreshImage(){
        if(StaticStorage.getCurrentTree()!=null && StaticStorage.getCurrentTree()!=tree){
            tree=StaticStorage.getCurrentTree();
            Graphics g = getImage().getGraphics();
            g.setColor(BACKGROUND_COLOR);
            g.fillRect(0, 0, getWidth(), getHeight());
            
            g.setColor(GUI.BORDER_COLOR);
            g.drawLine(0, getHeight()-1, getWidth(), getHeight()-1);
            
            BufferedImage txt = Font.stringToBufferedImage("On tree "+(StaticStorage.getIndexOfCurrentTree()+1)+" of "+StaticStorage.numTrees());
            g.drawImage(txt,(getWidth()-txt.getWidth())/2,(getHeight()-txt.getHeight())/2,null);
        }
    }
    
    @Override
    public void render(int xoff, int yoff, Screen screen){
        refreshImage();
        super.render(xoff, yoff, screen);
    }
    
    /**
     * changes the current Tree that is being looked at to the next right index
     */
    private class RightButton extends Button{
	public RightButton(int x, int y){
            super(x, y, ">");
	}
		
        @Override
	public void onAction(MouseEvent me){
            StaticStorage.incrementCurrentTree();
	}
    }
    
    /**
     * changes the current Tree that is being looked at to the next left index
     */
    private class LeftButton extends Button{
	public LeftButton(int x, int y){
            super(x, y, "<");
	}
		
        @Override
	public void onAction(MouseEvent me){
            StaticStorage.decrementCurrentTree();
	}
    }
    
}
