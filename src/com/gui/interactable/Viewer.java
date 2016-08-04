package com.gui.interactable;

import com.gui.gfx.Screen;
import com.main.DataSet;
import com.main.StaticStorage;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class Viewer extends Interactable{
    public static final Color BACKGROUND_COLOR = Color.WHITE;
    
    private TabSystem dataSetsTabSys;
    private TabSystem optionsTabSys;
    private TabSystem treesTabSys;
    private TreeStructure tree;

    public Viewer(int x, int y, int width, int height, Screen screen){
        super(x, y, width, height);
        init();

        initImage();

    }
    
    public final void init(){
        dataSetsTabSys = new TabSystem(0,
                                       0,
                                       getWidth());
        optionsTabSys = new TabSystem(0,
                                      dataSetsTabSys.getHeight(),
                                      getWidth());
        
        treesTabSys = new TabSystem(0,
                                      dataSetsTabSys.getHeight()+dataSetsTabSys.getHeight(),
                                      getWidth());
        tree = new TreeStructure(0,
                                 dataSetsTabSys.getHeight()+optionsTabSys.getHeight()+treesTabSys.getHeight(),
                                 getWidth(),
                                 getHeight()-dataSetsTabSys.getHeight()-optionsTabSys.getHeight()-treesTabSys.getHeight());
        optionsTabSys.addTab("Tree", tree);
        optionsTabSys.addTab("HI THERE", null);
        optionsTabSys.addTab("VERY LONG NAME TEST", null);
        optionsTabSys.addTab("S", null);
        for(int i=0;i<30; i++){
            treesTabSys.addTab(""+i, null);
        }
        //StaticStorage.getCurrentDataSet()
        addInteractable(dataSetsTabSys);
        addInteractable(optionsTabSys);
        addInteractable(treesTabSys);
        dataSetsTabSys.setSelectedTab(0);
        optionsTabSys.setSelectedTab(0);
        treesTabSys.setSelectedTab(0);
    }

    private void initImage(){
            Graphics g = getImage().getGraphics();
            g.setColor(BACKGROUND_COLOR);
            g.fillRect(getX(), getY(), getWidth(), getHeight());
            //refreshImage();
    }

    //ADD REMOVING
    public void refreshDataSetsTabSys(){
        if(dataSetsTabSys.numTabs()!=StaticStorage.numDataSets()){
            dataSetsTabSys.clear();
        }
        List<DataSet> dataSets = StaticStorage.getDataSets();
        for(int i=0; i<StaticStorage.numDataSets(); i++){
            
        }
    }

    @Override
    public void render(int xoff, int yoff, Screen screen){
        //refreshImage();
        super.render(xoff, yoff, screen);
    }
}
