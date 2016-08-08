
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

public class TreeStructure extends Interactable{
    private DecisionTree tree;
    private double previousY=0.;
    private int yoff;
    private List<Interactable> list;
    public static final double SCROLL_SPEED=2.;

    public TreeStructure(int x, int y, int width, int height) {
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
                getInteractables().get(i).render(getX()+xoff, getY()+yoff-this.yoff, screen);
            }
        }
        //super.render(xoff, yoff+this.yoff, screen);
    }
    
    @Override
    public void mouseDragged(MouseEvent me, int xoff, int yoff){
        this.yoff=(int)(this.yoff+(SCROLL_SPEED)*(previousY-me.getY()));
        
        this.yoff=(int)Math.max(this.yoff, 0);
        this.yoff=(int)Math.min(this.yoff, list.size()*Font.getCharHeight() - getHeight()*15/16);
        
        previousY=me.getY();
        super.mouseDragged(me, xoff, yoff);
    }
    
    @Override
    public void mouseHovered(MouseEvent me, int xoff, int yoff){
        previousY=me.getY();
        super.mouseHovered(me, xoff, yoff);
    }
    
    public void refreshImage(){
        if(StaticStorage.getCurrentTree()!=null && StaticStorage.getCurrentTree()!=tree){
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
                Interactable newTreeItem = new TreeItem(0,i*Font.getCharHeight(),bi);
                list.add(newTreeItem);
                addInteractable(newTreeItem);
            }
        }
    }
    
    private class TreeItem extends Interactable {

        public TreeItem(int x1, int y1, BufferedImage bi) {
            super(x1, y1, bi.getWidth(), bi.getHeight());
            setImage(bi);
        }
    }
    
}
