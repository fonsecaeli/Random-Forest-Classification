package com.gui.interactable;

import com.gui.gfx.Screen;
import com.main.DecisionTree;
import com.main.Node;
import com.main.StaticStorage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;

public class FishEye extends Interactable {
	ArrayList <Stack<Node>> stacks;
	DecisionTree[] trees;
	private static final Color BACKCOLOR = Color.WHITE;
	private int curIndex;
	public Button top;
	
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
		if (stacks.get(curIndex).empty()){
			Node node = trees[curIndex].getHeadNode();
		} else {
			Node node = stacks.get(curIndex).peek();
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
