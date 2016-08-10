package com.gui.interactable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.UIManager;
import com.main.Attribute;
import com.main.StaticStorage;
import com.main.RandomForest;
import com.main.Record;
import com.main.ImportData;
import com.main.DataSet;
import com.gui.gfx.Screen;
import com.gui.gfx.Font;


public class Query extends Interactable {
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    public static final double VERTICAL_SCROLL_SPEED=3.;
    public static final double HORIZONTAL_SCROLL_SPEED=1.;
    public static final int CONTENT_HORIZONTAL_SPACING = Viewer.CONTENT_HORIZONTAL_OFFSET;
    public static final int CONTENT_VERTICAL_SPACING = Viewer.CONTENT_VERTICAL_OFFSET;

    private HashMap <RandomForest, List<Interactable>> queried;
    private int yoff;
    private double previousY=0.;
    private int xoff;
    private double previousX=0.;
    private int VERTICAL_SHIFT;

    public Query(int x, int y, int width, int height){
        super(x, y, width, height);
        initImage();
        queried = new HashMap<>();
        addInteractable(new QueryButtonHolder(0,0,getWidth(),this));
        VERTICAL_SHIFT = getInteractables().get(0).getHeight()+CONTENT_VERTICAL_SPACING;
    }

    private void initImage(){
        Graphics g = getImage().getGraphics();
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void query(File file){
            List <Attribute> attributes = StaticStorage.getCurrentDataSet().getAttributes();
            DataSet newDataSet = ImportData.importData(file, attributes);
            RandomForest forest = StaticStorage.getCurrentRandomForest();
            queried.put(forest, new ArrayList());

            int sum = VERTICAL_SHIFT;
            for (Record r: newDataSet.getRecords()){
                    Interactable i = new DisplayItem(CONTENT_HORIZONTAL_SPACING,sum,Font.stringToBufferedImage(r.toString()+" Classification: "+forest.queryTrees(r, forest.getTrees())));
                    queried.get(forest).add(i);
                    sum+=Font.getCharHeight();
            }
    }

    private void clear(){
		List list = queried.get(StaticStorage.getCurrentRandomForest());
		if (list!=null)
			list.clear();
	}
        
    @Override
    public void render(int xoff, int yoff, Screen screen){
        screen.drawImage(getImage(), getX()+xoff, getY()+yoff);
        
        int start = (int)(this.yoff/Font.getCharHeight())-1;
        int end = (int)(this.yoff+getHeight())/Font.getCharHeight()+1;
        for(int i=start; i<end; i++){
            List<Interactable> list = queried.get(StaticStorage.getCurrentRandomForest());
            if(list!=null && i>=0 && i<list.size()){
                list.get(i).render(getX()+xoff-this.xoff, getY()+yoff-this.yoff, screen);
            }
        }
        //super.render(xoff, yoff+this.yoff, screen);
        
        for(Interactable i : getInteractables()){
            i.render(getX()+xoff, getY()+yoff, screen);
        }
    }
    
    /**
     * Manages the scrolling in the tree structure
     */
    @Override
    public void mouseDragged(MouseEvent me, int xoff, int yoff){
        if(queried.get(StaticStorage.getCurrentRandomForest())!=null){
            List<Interactable> list = queried.get(StaticStorage.getCurrentRandomForest());
            this.xoff=(int)(this.xoff+(HORIZONTAL_SCROLL_SPEED)*(previousX-me.getX()));

            int maxWidth=0;
            for(int i=0; i<list.size(); i++){
                if(list.get(i).getWidth()>maxWidth)
                    maxWidth = list.get(i).getWidth();
            }
            this.xoff=(int)Math.min(this.xoff, maxWidth - getWidth()+Font.getCharWidth()*6);
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
        }
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
	
    public class QueryButtonHolder extends Interactable {
        public static final int BUTTON_VERTICAL_SPACING = 6;
        public static final int BUTTON_HORIZONTAL_SPACING = 6;
        Query q;

        public QueryButtonHolder(int x1, int y1, int w, Query q) {
            super(x1, y1, w, Button.BUTTON_HEIGHT+2*BUTTON_VERTICAL_SPACING);
            this.q = q;
            init();
        }

        /**
         * Init the buttons, as well as adding them
         */
        private void init(){
            addInteractable(new QueryButton(CONTENT_HORIZONTAL_SPACING, BUTTON_VERTICAL_SPACING, q));
            addInteractable(new QueryClear(getWidth()-Button.getWidth(QueryClear.TITLE)-CONTENT_HORIZONTAL_SPACING,BUTTON_VERTICAL_SPACING,q));
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

        public class QueryButton extends Button {
                private Query dad;
                public static final String TITLE = "Query...";

                public QueryButton(int x, int y, Query dad){
                        super(x, y, TITLE);
                        this.dad = dad;
                }

                @Override
                public void onAction(MouseEvent me){
                        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
                        catch (Exception ex) {ex.printStackTrace();}
                        JFileChooser chooser = new JFileChooser();
                        chooser.setPreferredSize(new Dimension(960, 640));
                        chooser.setFileFilter(new FileNameExtensionFilter("csv files", "csv"));

                        int returnVal = chooser.showOpenDialog(null);
                        if (returnVal == JFileChooser.APPROVE_OPTION){
                                String filePath = chooser.getSelectedFile().getPath();
                                if (filePath.substring(filePath.length()-4).equals(".csv")){
                                        System.out.println("You choose to open this file: "+filePath);
                                        dad.query(chooser.getSelectedFile());
                                }
                        }
                }
        }

        public class QueryClear extends Button {
		private Query dad;
                public static final String TITLE = "Clear";
		
		public QueryClear (int x, int y, Query dad){
			super(x, y, TITLE);
			this.dad = dad;
		}
		
		@Override
		public void onAction(MouseEvent me){
			dad.clear();
		}
	}
}
    
    /**
     * basically one item that is displayed
     */
    public class DisplayItem extends Interactable {

        public DisplayItem(int x1, int y1, BufferedImage bi) {
            super(x1, y1, bi.getWidth(), bi.getHeight());
            setImage(bi);
        }
    }
}
