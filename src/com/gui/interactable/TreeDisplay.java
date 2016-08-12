
package com.gui.interactable;

import com.gui.gfx.Font;
import com.gui.gfx.Screen;
import com.main.DecisionTree;
import com.main.StaticStorage;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class TreeDisplay extends Interactable{
    public static final double VERTICAL_SCROLL_SPEED=3.;
    public static final double HORIZONTAL_SCROLL_SPEED=1.;
    public static final int CONTENT_HORIZONTAL_SPACING = Viewer.CONTENT_HORIZONTAL_OFFSET;
    public static final int CONTENT_VERTICAL_SPACING = Viewer.CONTENT_VERTICAL_OFFSET;
    
    private double previousY=0.;
    private int yoff;
    private int xoff;
    private double previousX=0.;
    
    private DecisionTree tree;
    private List<Interactable> list;

    public TreeDisplay(int x, int y, int width, int height) {
        super(x, y, width, height);
        list = new ArrayList<>();
    }
    
    @Override
    public void render(int xoff, int yoff, Screen screen){
        refreshImage();
        screen.drawImage(getImage(), getX()+xoff, getY()+yoff);
        
        int start = (int)(this.yoff/Font.getCharHeight())-1;
        int end = (int)(this.yoff+getHeight())/Font.getCharHeight()+1;
        for(int i=start; i<end; i++){
            if(i>=0 && i<getInteractables().size()){
                getInteractables().get(i).render(getX()+xoff-this.xoff, getY()+yoff-this.yoff, screen);
            }
        }
        //super.render(xoff, yoff+this.yoff, screen);
    }
    
    /**
     * Manages the scrolling in the tree structure
     */
    @Override
    public void mouseDragged(MouseEvent me, int xoff, int yoff){
        this.xoff=(int)(this.xoff+(HORIZONTAL_SCROLL_SPEED)*(previousX-me.getX()));

        int maxWidth=0;
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getWidth()>maxWidth)
                maxWidth = list.get(i).getWidth();
        }
        this.xoff=(int)Math.min(this.xoff, maxWidth - getWidth());
        this.xoff=(int)Math.max(this.xoff, 0);
        previousX=me.getX();
            
            
        this.yoff=(int)(this.yoff+(VERTICAL_SCROLL_SPEED)*(previousY-me.getY()));
        
        this.yoff=(int)Math.max(this.yoff, 0);
        int maxHeight = getHeight()*15/16;
        int height = list.size()*Font.getCharHeight();
        if(height>maxHeight)
            this.yoff=(int)Math.min(this.yoff, height - maxHeight);
        else 
            this.yoff=(int)Math.min(this.yoff, 0);
        
        previousY=me.getY();
        super.mouseDragged(me, xoff, yoff);
    }
    
    /**
     * Manages the scrolling in the tree structure
     */
    @Override
    public void mouseHovered(MouseEvent me, int xoff, int yoff){
        previousX=me.getX();
        previousY=me.getY();
        super.mouseHovered(me, xoff, yoff);
    }
    
    /**
     * Refreshes the image of the tree structure, mainly of the strings that need to be drawn
     */
    public void refreshImage(){
        if(StaticStorage.getCurrentTree()!=null && StaticStorage.getCurrentTree()!=tree){
            this.xoff=0;
            this.yoff=0;
            for(Interactable i : list){
                removeInteractable(i);
            }
            list = new ArrayList<>();
            
            tree=StaticStorage.getCurrentTree();
            Graphics g = getImage().getGraphics();
            g.setColor(Viewer.BACKGROUND_COLOR);
            g.fillRect(0, 0, getWidth(), getHeight());
            //g.drawImage(Font.stringToBufferedImage(tree.toString()), 0, 0, null);
            //g.drawImage(bis.get(i), 0, 0, null);
            List<BufferedImage> bis = Font.stringToBufferedImageList(StaticStorage.getCurrentTree().toString());
            for(int i=0; i<bis.size(); i++){
                BufferedImage bi = bis.get(i);
                //System.out.println("i: "+i+" | size: "+bis.size()+" | BI: "+bi);
                Interactable newTreeItem = new TreeItem(CONTENT_HORIZONTAL_SPACING,CONTENT_VERTICAL_SPACING+i*Font.getCharHeight(),bi);
                list.add(newTreeItem);
                addInteractable(newTreeItem);
            }
        }
    }
    
    /**
     * basically one item that is displayed in the tree display
     */
    private class TreeItem extends Interactable {

        public TreeItem(int x1, int y1, BufferedImage bi) {
            super(x1, y1, bi.getWidth(), bi.getHeight());
            setImage(bi);
        }
    }
    
}
