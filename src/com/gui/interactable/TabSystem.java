
package com.gui.interactable;

import com.gui.gfx.Screen;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class TabSystem extends Interactable{
    public static final int TAB_HORIZONTAL_SPACING = 4,
                            TAB_VERTICAL_SPACING = 8;
    public static final Color BACKGROUND_COLOR = Color.WHITE;
    
    private List<Pair<Tab, Interactable>> pairList;
    private Tab selectedTab;
    
    public TabSystem(int x1, int y1, int w) {
        super(x1, y1, w, Button.BUTTON_HEIGHT+TAB_VERTICAL_SPACING);
        pairList = new ArrayList<>();
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
        for(Pair p : pairList){
            ((Tab)p.getKey()).refreshImage();
        }
    }
    
    public void addTab(String name, Interactable i){
        Tab newTab = new Tab(totalWidth()+TAB_HORIZONTAL_SPACING, TAB_VERTICAL_SPACING, name, this);
        pairList.add(new Pair<>(newTab, i));
        addInteractable(newTab);
        selectedTab = newTab;
        refreshImage();
    }
    
    public void addTab(Tab newTab, Interactable i){
        pairList.add(new Pair<>(newTab, i));
        addInteractable(newTab);
        selectedTab = newTab;
        refreshTabs();
        refreshImage();
    }
    
    public void setSelectedTab(int index){
        if(index<pairList.size() && index>=0){
            selectedTab = pairList.get(index).getKey();
        }
    }
    
    public void setSelectedTab(Tab t){
        selectedTab = t;
    }
    
    public Tab getSelectedTab(){
        return selectedTab;
    }
    
    public int getSelectedTabIndex(){
        return getIndexOfTab(selectedTab);
    }
    
    public int getIndexOfTab(Tab t){
        for (int i=0; i<pairList.size(); i++) {
            if (pairList.get(i).getKey() == t) {
                return i;
            }
        }
        return -1;
    }
    
    public int totalWidth(){
        int sum=0;
        for(Pair p : pairList){
            sum+=((Tab)p.getKey()).getImage().getWidth();
        }
        sum+=pairList.size()*TAB_HORIZONTAL_SPACING;
        return sum;
    }
    
    public int totalWidth(int max){
        int sum=0;
        for(int i=0;i<max;i++){
            sum+=pairList.get(i).getKey().getImage().getWidth();
        }
        sum+=max*TAB_HORIZONTAL_SPACING;
        return sum;
    }
    
    protected void removeTab(Pair p){
        removeInteractable((Tab)p.getKey());
        pairList.remove(p);
        refreshTabs();
    }
    
    public int numTabs(){
        return pairList.size();
    }
    
    public void clear(){
        while(numTabs()>0)
            removeTab(pairList.get(0));
    }
    
    public Interactable get(Tab t){
        int index = getIndexOfTab(t);
        return index<0 ? null: pairList.get(index).getValue();
    }
    
    @Override
    public void render(int xoff, int yoff, Screen screen){
        super.render(xoff, yoff, screen);
        
        //render the current tab's display
        Interactable current = get(selectedTab);
        if(current!=null){
            current.render(xoff, yoff, screen);
        }
    }
    
    private void refreshTabs(){
        for(int i=0; i<pairList.size(); i++){
            int currentLength = totalWidth(i);
            pairList.get(i).getKey().setX(currentLength+TAB_HORIZONTAL_SPACING);
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