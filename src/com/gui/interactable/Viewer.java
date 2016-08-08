package com.gui.interactable;

import com.gui.gfx.Screen;
import com.main.DataSet;
import com.main.StaticStorage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.List;

public class Viewer extends Interactable{
    public static final Color BACKGROUND_COLOR = Color.WHITE;
    
    private TabSystem dataSetsTabSys;
    private TabSystem optionsTabSys;
    private CurrentTreeChanger treeChanger;
    private TreeStructure tree;
    private FishEye fishEye;

    public Viewer(int x, int y, int width, int height){
        super(x, y, width, height);
        init();

        initImage();

    }
    
    public final void init(){
        int verticalSum=0; //how far down new elements should be added
    /********************************************************************************************************************************/
        dataSetsTabSys = new TabSystem(0,
                                       verticalSum,
                                       getWidth());
        verticalSum+=dataSetsTabSys.getHeight();
    /********************************************************************************************************************************/
        optionsTabSys = new TabSystem(0,
                                      verticalSum,
                                      getWidth());
        verticalSum+=optionsTabSys.getHeight();
    /********************************************************************************************************************************/
        treeChanger = new CurrentTreeChanger(0,
                                             verticalSum,
                                             getWidth());
        verticalSum+=treeChanger.getHeight();
    /********************************************************************************************************************************/
        tree = new TreeStructure(0,
                                 verticalSum,
                                 getWidth(),
                                 getHeight()-verticalSum);
    /****************************************************************************************************/
        fishEye = new FishEye(0,
                              verticalSum,
                              getWidth(),
                              getHeight()-verticalSum);
    /********************************************************************************************************************************/
        optionsTabSys.addTab("Tree", tree);
        optionsTabSys.addTab("Fish Eye Viewer", fishEye);
        addInteractable(dataSetsTabSys);
        addInteractable(optionsTabSys);
        addInteractable(treeChanger);
        dataSetsTabSys.setSelectedTab(0);
        optionsTabSys.setSelectedTab(1);//1 is tested, it puts fish eye at index 0 even though it was added second
    }

    private void initImage(){
            Graphics g = getImage().getGraphics();
            g.setColor(BACKGROUND_COLOR);
            g.fillRect(0, 0, getWidth(), getHeight());
            //refreshImage();
    }

    //ADD REMOVING
    public void refreshDataSetsTabSys(){
        if(dataSetsTabSys.numTabs()!=StaticStorage.numDataSets()){
            dataSetsTabSys.clear();
            List<DataSet> dataSets = StaticStorage.getDataSets();
            for(int i=0; i<StaticStorage.numDataSets(); i++){
                dataSetsTabSys.addTab(new DataSetTab(0, TabSystem.TAB_VERTICAL_SPACING, dataSetsTabSys, dataSets.get(i)), null);
            }
        }
        if(dataSetsTabSys.getSelectedTab()!=null)
           if(StaticStorage.getCurrentDataSet()!=((DataSetTab)(dataSetsTabSys.getSelectedTab())).getDataSet())
                StaticStorage.setIndex(dataSetsTabSys.getSelectedTabIndex());
    }
    
    @Override
    public void mouseClicked(MouseEvent me, int xoff, int yoff){
        super.mouseClicked(me, xoff, yoff);
        if(optionsTabSys.getSelectedTab()!=null)optionsTabSys.get(optionsTabSys.getSelectedTab()).mouseClicked(me, xoff, yoff);
    }

    @Override
    public void mousePressed(MouseEvent me, int xoff, int yoff){
        super.mousePressed(me, xoff, yoff);
        if(optionsTabSys.getSelectedTab()!=null)optionsTabSys.get(optionsTabSys.getSelectedTab()).mousePressed(me, xoff, yoff);
    }

    @Override
    public void mouseReleased(MouseEvent me, int xoff, int yoff){
        super.mouseReleased(me, xoff, yoff);
        if(optionsTabSys.getSelectedTab()!=null)optionsTabSys.get(optionsTabSys.getSelectedTab()).mouseReleased(me, xoff, yoff);
    }

    @Override
    public void mouseNotHovered(MouseEvent me, int xoff, int yoff){
        super.mouseNotHovered(me, xoff, yoff);
        if(optionsTabSys.getSelectedTab()!=null)optionsTabSys.get(optionsTabSys.getSelectedTab()).mouseNotHovered(me, xoff, yoff);
    }

    @Override
    public void mouseHovered(MouseEvent me, int xoff, int yoff){
        super.mouseHovered(me, xoff, yoff);
        if(optionsTabSys.getSelectedTab()!=null)optionsTabSys.get(optionsTabSys.getSelectedTab()).mouseHovered(me, xoff, yoff);
    }

    @Override
    public void mouseDragged(MouseEvent me, int xoff, int yoff){
        super.mouseDragged(me, xoff, yoff);
        if(optionsTabSys.getSelectedTab()!=null)optionsTabSys.get(optionsTabSys.getSelectedTab()).mouseDragged(me, xoff, yoff);
    }

    @Override
    public void render(int xoff, int yoff, Screen screen){
        //refreshImage();
        refreshDataSetsTabSys();
        super.render(xoff, yoff, screen);
    }
    
    private class DataSetTab extends Tab {
        private DataSet ds;
        
        public DataSetTab(int x, int y, TabSystem ts, DataSet d) {
            super(x,y,d.getName(),ts);
            ds = d;
        }
        
        public DataSet getDataSet(){
            return ds;
        }
    }
}
