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
	private HashMap <RandomForest, List<BufferedImage>> queried;
	
	public Query(int x, int y, int width, int height){
		super(x, y, width, height);
		initImage();
		queried = new HashMap<>();
		addInteractable(new QueryButton(0, 0, this));
		addInteractable(new QueryClear(0, 64, this));
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
		queried.put(forest, new ArrayList<BufferedImage>());
		
		for (Record r: newDataSet.getRecords())
			queried.get(forest).add(Font.stringToBufferedImage(r.toString()+" Classification: "+forest.queryTrees(r, forest.getTrees())));
	}
	
	private void clear(){
		List <BufferedImage> list = queried.get(StaticStorage.getCurrentRandomForest());
		if (list!=null)
			list.clear();
	}
	
	public class QueryButton extends Button {
		private Query dad;
		
		public QueryButton(int x, int y, Query dad){
			super(x, y, "Query...");
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
		
		public QueryClear (int x, int y, Query dad){
			super(x, y, "Clear");
			this.dad = dad;
		}
		
		@Override
		public void onAction(MouseEvent me){
			dad.clear();
		}
	}
}
