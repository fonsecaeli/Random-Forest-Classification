package com.gui.interactable;

import com.main.*;
import java.util.Stack;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.image.BufferedImage;
import com.gui.gfx.Screen;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;

public class FishEye extends Interactable {
	ArrayList <Stack<Node>> stacks;
	DecisionTree[] trees;
	private static final Color BACKCOLOR = Color.WHITE;
	private int curIndex;
	private Button top;
	
	public FishEye(int x, int y, int width, int height){
		super(x, y, width, height);
		initImage();
		init();
		addInteractable(new LeftButton(0, (getHeight()-Button.getHeight("<"))/3, this));
		addInteractable(new RightButton(getWidth()-Button.getWidth(">") , (getHeight()-Button.getHeight(">"))/3, this));
	}
	
	private void initImage(){
		Graphics g = getImage().getGraphics();
		g.setColor(BACKCOLOR);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	private void init(){
		if (StaticStorage.getCurrentRandomForest()!=null){
			trees = StaticStorage.getCurrentRandomForest().getTrees();
			stacks = new ArrayList<Stack<Node>>(trees.length);
			curIndex = 0;
		}
	}
	
	public void render(int xOff, int yOff, Screen screen){
		refreshData();
	}
	
	private void refreshData(){
		if (StaticStorage.getCurrentRandomForest()!=null&&
			StaticStorage.getCurrentRandomForest().getTrees()!=trees){
			trees = StaticStorage.getCurrentRandomForest().getTrees();
			stacks = new ArrayList<Stack<Node>>(trees.length);
			curIndex = 0;
			refreshImage();
		}
	}
	
	public void refreshImage(){
		if (stack.get(curIndex).empty()){
			stacks.get(curIndex).push(trees[curIndex].getHeadNode());
		}
		if (stacks.get(curIndex).size()>=1){
			Node node = stacks.get(curIndex).pop();
			if (top!=null) removeInteractable(top);
			top = new FishEyeButton(getHeight()/4 , (getWidth()-Button.getWidth())/2,
						this, -1, stacks.get(curIndex).peek().getAttribute().getName());
			addInteractable(top);
			stacks.get(curIndex).push(node);
		}
		
		
	}
	
	protected void incrementIndex(){
		if (trees!=null){
			curIndex++;
			curIndex%=trees.length;
			System.out.println(curIndex);
			refreshImage();
		}
	}
	
	protected void decrementIndex(){
		if (trees!=null){
			curIndex--;
			if (curIndex<0) curIndex+=trees.length;
			System.out.println(curIndex);
			refreshImage();
		}
	}
	
	public void clicked(int index){
		if (index<0){
			
		}
	}
	
	public class FishEyeButton extends Button{
		private FishEye dad;
		int index;
		public FishEyeButton(int x, int y, FishEye dad, int index, String text){
			super(x, y, text);
			this.dad = dad;
			this.index = index;
		}
		
		public void onAction(MouseEvent me){
			dad.clicked(index);
		}
	}
	
	public class RightButton extends Button{
		private FishEye dad;
		public RightButton(int x, int y, FishEye dad){
			super(x, y, ">");
			this.dad = dad;
		}
		
		public void onAction(MouseEvent me){
			dad.incrementIndex();
		}
	}
	
	public class LeftButton extends Button{
		private FishEye dad;
		public LeftButton(int x, int y, FishEye dad){
			super(x, y, "<");
			this.dad = dad;
		}
		
		public void onAction(MouseEvent me){
			dad.decrementIndex();
		}
	}
}
