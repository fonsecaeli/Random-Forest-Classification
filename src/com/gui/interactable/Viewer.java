package com.gui.interactable;

import com.gui.gfx.Screen;
import java.awt.Color;
import java.awt.Graphics;

public class Viewer extends Interactable{
    public static final Color BACKGROUND_COLOR = Color.WHITE;
    
    private TabSystem dataSetsTabSys;
    private TabSystem optionsTabSys;
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
        tree = new TreeStructure(0,
                                 optionsTabSys.getHeight(),
                                 getWidth(),
                                 getHeight()-dataSetsTabSys.getHeight()-optionsTabSys.getHeight());
        optionsTabSys.addTab("Tree", tree);
        optionsTabSys.addTab("HI THERE", null);
        optionsTabSys.addTab("VERY LONG NAME TEST", null);
        optionsTabSys.addTab("S", null);
        //StaticStorage.getCurrentDataSet()
        addInteractable(dataSetsTabSys);
        addInteractable(optionsTabSys);
        optionsTabSys.setSelectedTab(0);
    }

    private void initImage(){
            Graphics g = getImage().getGraphics();
            g.setColor(BACKGROUND_COLOR);
            g.fillRect(getX(), getY(), getWidth(), getHeight());
            //refreshImage();
    }


    @Override
    public void render(int xoff, int yoff, Screen screen){
        //refreshImage();
        super.render(xoff, yoff, screen);
    }
}
