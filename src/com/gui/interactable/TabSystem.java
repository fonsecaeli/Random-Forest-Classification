
package com.gui.interactable;

import com.gui.gfx.Font;
import com.gui.gfx.Screen;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TabSystem extends Interactable{
    public static final int TAB_HORIZONTAL_SPACING = 4,
                            TAB_VERTICAL_SPACING = 8;
    public static final Color BACKGROUND_COLOR = Color.WHITE,
                              SELECTED_COLOR = Color.WHITE,
                              UNSELECTED_COLOR = new Color(240, 240, 240);
    
    private Map<Tab, Interactable> tabSet;
    private Set<Tab> keys;
    private Tab selectedTab;
    
    public TabSystem(int x1, int y1, int w) {
        super(x1, y1, w, Button.BUTTON_HEIGHT+TAB_VERTICAL_SPACING);
        tabSet = new HashMap<>();
        keys = tabSet.keySet();
        selectedTab = null;
        initImage();
    }
    
    public void initImage(){
        Graphics g = getImage().getGraphics();
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(GUI.BORDER_COLOR);
        g.drawLine(0, getHeight()-1, getWidth(), getHeight()-1);
    }
    
    public void refreshImage(){
        for(Tab key : keys){
            key.refreshImage();
        }
    }
    
    public void addTab(String name, Interactable i){
        Tab newTab = new Tab(totalWidth()+TAB_HORIZONTAL_SPACING, TAB_VERTICAL_SPACING, name, this);
        tabSet.put(newTab, i);
        addInteractable(newTab);
        selectedTab = newTab;
        refreshImage();
    }
    
    public void setSelectedTab(int index){
        if(index<keys.size() && index>=0){
            List<Tab> tabs = list();
            selectedTab = tabs.get(index);
        }
    }
    
    public int totalWidth(){
        int sum=0;
        for(Tab key : keys){
            sum+=key.getImage().getWidth();
        }
        sum+=tabSet.size()*TAB_HORIZONTAL_SPACING;
        return sum;
    }
    
    public int totalWidth(int max){
        int sum=0;
        List<Tab> tabs = list();
        for(int i=0;i<max;i++){
            
            sum+=tabs.get(i).getImage().getWidth();
        }
        sum+=max*TAB_HORIZONTAL_SPACING;
        return sum;
    }
    
    protected void removeTab(Tab t){
        removeInteractable(t);
        tabSet.remove(t);
        refreshTabs();
    }
    
    public int numTabs(){
        return keys.size();
    }
    
    private List<Tab> list(){
        List<Tab> tabs= new ArrayList<>();
        for(Tab key : keys){
            tabs.add(key);
        }
        return tabs;
    } 
    
    public void clear(){
        tabSet = new HashMap<>();
        keys = tabSet.keySet();
        selectedTab = null;
    }
    
    @Override
    public void render(int xoff, int yoff, Screen screen){
        super.render(xoff, yoff, screen);
        
        //render the current tab's display
        Interactable current = tabSet.get(selectedTab);
        if(current!=null){
            current.render(xoff, yoff+getHeight(), screen);
        }
    }
    
    private void refreshTabs(){
        List<Tab> tabs = list();
        
        for(int i=0; i<tabs.size(); i++){
            int currentLength = totalWidth(i);
            tabs.get(i).setX(currentLength+TAB_HORIZONTAL_SPACING);
        }
    }
    
    protected class Tab extends Button{
        
        private TabSystem tabSys; //for finding which is selected
        
        public Tab(int x, int y, String name, TabSystem ts) {
            super(x, y, name);
            tabSys = ts;
            initImage();
            refreshImage();
            
        }
        
        @Override
        public void refreshImage(){
            if(tabSys!=null){
                Graphics g = getImage().getGraphics();
                
                if(isSelectedTab()){

                    g.setColor(HOVER_COLOR);
                    g.drawRect(0, 0, getWidth()-1, getHeight());

                
                    g.setColor(SELECTED_COLOR);
                    g.fillRect(1, 1, getWidth()-2, getHeight()-1);
                    BufferedImage text = Font.stringToBufferedImage(getName());
                    g.drawImage(text, XBORDER, YBORDER, null);
                    
                } else {
                    
                    if (getStatus() == OFF)
                            g.setColor(OFF_COLOR);
                    else if (getStatus() == HOVER || isSelectedTab())
                            g.setColor(HOVER_COLOR);
                    else if (getStatus() == CLICK)
                            g.setColor(CLICK_COLOR);
                    else g.setColor(NULL_COLOR);

                    g.drawRect(0, 0, getWidth()-1, getHeight()-1);
                    g.setColor(GUI.BORDER_COLOR);
                    g.drawLine(0, getHeight()-1, getWidth()-1, getHeight()-1);
                    
                    g.setColor(UNSELECTED_COLOR);
                    g.fillRect(1, 1, getImage().getWidth()-2, getImage().getHeight()-2);
                    BufferedImage text = Font.stringToBufferedImage(getName());
                    g.drawImage(text, XBORDER, YBORDER, null);
                }
            }
        }
        
        public boolean isSelectedTab(){
            return tabSys.selectedTab == this;
        }

        protected void initImage(){
            Graphics g = getImage().getGraphics();
            g.setColor(Button.BACKGROUND_COLOR);
            g.fillRect(1, 1, getImage().getWidth()-2, getImage().getHeight()-2);
            BufferedImage text = Font.stringToBufferedImage(getName());
            g.drawImage(text, XBORDER, YBORDER, null);
        }
        
        @Override
        public void onAction(MouseEvent me){
            tabSys.selectedTab=this;
            tabSys.refreshImage();
        }
        
    }
    
    /* Yeah, this is too much work. Don't import so many datasets or something. Maybe cap the number of tabs. idk
    public void refreshImage(){
        Graphics g = getImage().getGraphics();
        
        List<Tab> tabs= new ArrayList<>();
        for(Tab key : keys){
            tabs.add(key);
        }
        
        //draw the tabs
        int currentLength=0;
        int finalIndex = 0;
        for(int i=0; (currentLength=totalWidth(i))<getWidth() && i<tabs.size(); i++){
            tabs.get(i).render(xoff+currentLength, yoff, screen);
            finalIndex=i;
        }
        currentLength = totalWidth(finalIndex);
        int width = Font.getCharWidth();
        BufferedImage ellipsis = new BufferedImage(width*(int)((getWidth()-currentLength)/width), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = ellipsis.
        for(int i=0; i<ellipsis.getWidth(); i+=Font.getCharWidth()){
            
        }
    }
    */
}