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
    public static final int CONTENT_VERTICAL_OFFSET = 6, 
                            CONTENT_HORIZONTAL_OFFSET = 6;
    
    //The tab system that changes the current data set
    private TabSystem dataSetsTabSys;
    //The tab system which updates the current display
    private TabSystem optionsTabSys;
    //The holder for the buttons which change the current tree
    private CurrentTreeChanger treeChanger;
    //The tree display
    private TreeDisplay treeDisplay;
    //The fisheye display
    private FishEye fishEyeDisplay;
    //The query display
    private Query queryDisplay;
    //The full decision tree display
    //private FullDecisionTreeDisplay fullDecisionTreeDisplay;

    /**
     * The basic constructor, passes the parameters onto Interactable and then calls its init methods
     * @param x
     * @param y
     * @param width
     * @param height 
     */
    public Viewer(int x, int y, int width, int height){
        super(x, y, width, height);
        init();

        initImage();
    }
    
    /**
     * Inits the tab systems, buttons, etc.
     * Also places them and adds them as listeners
     */
    public final void init(){
        int verticalSum=0; //how far down new elements should be added
    /************************************************************************************************************/
        dataSetsTabSys = new TabSystem(0, verticalSum, getWidth());
        verticalSum+=dataSetsTabSys.getHeight();
    /************************************************************************************************************/
        optionsTabSys = new TabSystem(0, verticalSum, getWidth());
        verticalSum+=optionsTabSys.getHeight();
    /************************************************************************************************************/
        treeChanger = new CurrentTreeChanger(0, verticalSum, getWidth());
        verticalSum+=treeChanger.getHeight();
    /*************************************************************************************************************/
        treeDisplay = new TreeDisplay(0, verticalSum, getWidth(), getHeight()-verticalSum);
    /****************************************************************************************************/
        fishEyeDisplay = new FishEye(0, verticalSum, getWidth(), getHeight()-verticalSum);
    /************************************************************************************************************/
	queryDisplay = new Query(0, verticalSum, getWidth(), getHeight()-verticalSum);
    /************************************************************************************************************/
	//fullDecisionTreeDisplay = new FullDecisionTreeDisplay(0, verticalSum, getWidth(), getHeight()-verticalSum);
    /************************************************************************************************************/
    
        optionsTabSys.addTab("Tree", treeDisplay);
        optionsTabSys.addTab("Fish Eye Viewer", fishEyeDisplay);
	optionsTabSys.addTab("Query", queryDisplay);
	//optionsTabSys.addTab("Full Decision Tree", fullDecisionTreeDisplay);
        addInteractable(dataSetsTabSys);
        addInteractable(optionsTabSys);
        addInteractable(treeChanger);
        dataSetsTabSys.setSelectedTab(0);
        optionsTabSys.setSelectedTab(2);//1 is tested, it puts fish eye at index 0 even though it was added second
    }

    /**
     * Initializes the Viewer's image
     */
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
        Tab selected = optionsTabSys.getSelectedTab();
        if(selected!=null && optionsTabSys.get(selected)!=null)optionsTabSys.get(selected).mouseClicked(me, xoff, yoff);
    }

    @Override
    public void mousePressed(MouseEvent me, int xoff, int yoff){
        super.mousePressed(me, xoff, yoff);
        Tab selected = optionsTabSys.getSelectedTab();
        if(selected!=null && optionsTabSys.get(selected)!=null)optionsTabSys.get(selected).mousePressed(me, xoff, yoff);
    }

    @Override
    public void mouseReleased(MouseEvent me, int xoff, int yoff){
        super.mouseReleased(me, xoff, yoff);
        Tab selected = optionsTabSys.getSelectedTab();
        if(selected!=null && optionsTabSys.get(selected)!=null)optionsTabSys.get(selected).mouseReleased(me, xoff, yoff);
    }

    @Override
    public void mouseNotHovered(MouseEvent me, int xoff, int yoff){
        super.mouseNotHovered(me, xoff, yoff);
        Tab selected = optionsTabSys.getSelectedTab();
        if(selected!=null && optionsTabSys.get(selected)!=null)optionsTabSys.get(selected).mouseNotHovered(me, xoff, yoff);
    }

    @Override
    public void mouseHovered(MouseEvent me, int xoff, int yoff){
        super.mouseHovered(me, xoff, yoff);
        Tab selected = optionsTabSys.getSelectedTab();
        if(selected!=null && optionsTabSys.get(selected)!=null)optionsTabSys.get(selected).mouseHovered(me, xoff, yoff);
    }

    @Override
    public void mouseDragged(MouseEvent me, int xoff, int yoff){
        super.mouseDragged(me, xoff, yoff);
        Tab selected = optionsTabSys.getSelectedTab();
        if(selected!=null && optionsTabSys.get(selected)!=null)optionsTabSys.get(selected).mouseDragged(me, xoff, yoff);
    }

    @Override
    public void render(int xoff, int yoff, Screen screen){
        if(StaticStorage.numDataSets()<=0){
            screen.drawImage(getImage(), getX()+xoff, getY()+yoff);
            dataSetsTabSys.render(getX()+xoff, getY()+yoff, screen);
        } else {
            //refreshImage();
            refreshDataSetsTabSys();
            super.render(xoff, yoff, screen);
        }
    }
    
    /**
     * the tabs which change the current Data Set
     */
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
