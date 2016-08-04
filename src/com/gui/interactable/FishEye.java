package com.gui.interactable;

import com.main.*;
import java.util.Stack;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import java.awt.Graphics;

public class FishEye extends Interactable {
	ArrayList <Stack<Node>> stacks;
	DecisionTree[] trees;
	private static final Color BACKCOLOR = Color.WHITE;
	private int curIndex;
	
	public FishEye(int x, int y, int width, int height){
		super(x, y, width, height);
		initImage();
		init();
		addInteractable(new LeftButton(0, (getHeight()/2)-(Button.getHeight("<")/2), this));
		addInteractable(new RightButton(getWidth()-Button.getWidth(">") , (getHeight()/2)-(Button.getHeight(">")/2), this));
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
	
	public void refreshImage(){
		if (StaticStorage.getCurrentRandomForest()!=null&&
			StaticStorage.getCurrentRandomForest().getTrees()!=trees){
			trees = StaticStorage.getCurrentRandomForest().getTrees();
			stacks = new ArrayList<Stack<Node>>(trees.length);
			curIndex = 0;
		} 
	}
	
	protected void add(){
		curIndex++;
		curIndex%=trees.length;
	}
	
	protected void subtract(){
		curIndex--;
		if (curIndex<0) curIndex+=trees.length;
	}
	
	public class RightButton extends Button{
		private FishEye dad;
		public RightButton(int x, int y, FishEye dad){
			super(x, y, ">");
			this.dad = dad;
		}
		
		public void onAction(MouseEvent me){
			dad.add();
		}
	}
	
	public class LeftButton extends Button{
		private FishEye dad;
		public LeftButton(int x, int y, FishEye dad){
			super(x, y, "<");
			this.dad = dad;
		}
		
		public void onAction(MouseEvent me){
			dad.subtract();
		}
	}
}
