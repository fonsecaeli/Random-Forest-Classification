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
	private FishEyeButton top;
	private Button middle;
	private ArrayList<FishEyeButton> bottom;
	private boolean change;
	
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
			change = false;
		}
	}
	
	public void render(int xOff, int yOff, Screen screen){
		refreshData();
		super.render(xOff, yOff, screen);
	}
	
	private void refreshData(){
		if (StaticStorage.getCurrentRandomForest()!=null&&
			StaticStorage.getCurrentRandomForest().getTrees()!=trees){
			trees = StaticStorage.getCurrentRandomForest().getTrees();
			stacks = new ArrayList<Stack<Node>>();
			for (int i=0; i<trees.length; i++)
				stacks.add(new Stack<Node>());
			curIndex = 0;
			change = true;
		}
		if (change) refreshImage();
	}
	
	public void refreshImage(){
		if (stacks.get(curIndex).empty()){
			stacks.get(curIndex).push(trees[curIndex].getHeadNode());
		}
		if (stacks.get(curIndex).size()>=2){
			Node node = stacks.get(curIndex).pop();
			if (top!=null) removeInteractable(top);
			String name = stacks.get(curIndex).peek().getAttribute().getName();
			top = new FishEyeButton((getWidth()-Button.getWidth(name))/2 , 
						(getHeight()-Button.getHeight(name))/4,
						this, -1, name);
			addInteractable(top);
			stacks.get(curIndex).push(node);
		} else {
			removeInteractable(top);
			top = null;
		}
		if (middle!=null) removeInteractable(middle);
		String name = stacks.get(curIndex).peek().getAttribute().getName();
		middle = new Button((getWidth()-Button.getWidth(name))/2,
					(getHeight()-Button.getHeight(name))/2, name);
		addInteractable(middle);
		if (bottom!=null)
			for (FishEyeButton f: bottom)
				removeInteractable(f);
		bottom = new ArrayList<>();
		Node node = stacks.get(curIndex).peek();
		int sum = 0;
		for (String a: node.getKeys()){
			if (isLeafNode(stacks.get(curIndex).peek().getChild(a))) sum+=Button.getWidth(stacks.get(curIndex).peek().getChild(a).getDecision());
			else sum+=Button.getWidth(a)+10;
		}
		sum-=10;
		sum = (getWidth()-sum)/2;
		int i=0;
		for (String a: node.getKeys()){
			if (isLeafNode(stacks.get(curIndex).peek().getChild(a))) {
				bottom.add(new FishEyeButton(sum, (getHeight()-Button.getHeight(a))*3/4, this, i, stacks.get(curIndex).peek().getChild(a).getDecision()));
				sum+=Button.getWidth(stacks.get(curIndex).peek().getChild(a).getDecision())+10;
			}
			else {
				bottom.add(new FishEyeButton(sum, (getHeight()-Button.getHeight(a))*3/4, this, i, a));
				sum+=Button.getWidth(a)+10;
			}
			addInteractable(bottom.get(bottom.size()-1));
			i++;
		}
		change = false;
	}
	
	private boolean isLeafNode(Node node){
		return (node.getDecision()!=null);
	}
	
	protected void incrementIndex(){
		if (trees!=null){
			curIndex++;
			curIndex%=trees.length;
			System.out.println(curIndex);
			change = true;
		}
	}
	
	protected void decrementIndex(){
		if (trees!=null){
			curIndex--;
			if (curIndex<0) curIndex+=trees.length;
			System.out.println(curIndex);
			change = true;
		}
	}
	
	public void clicked(int index){
		if (index<0){
			stacks.get(curIndex).pop();
			change = true;
		} else if (index>=0&&index<bottom.size()){
			if (!isLeafNode(stacks.get(curIndex).peek().getChild(bottom.get(index).getName()))){
				stacks.get(curIndex).push(stacks.get(curIndex).peek().getChild(bottom.get(index).getName()));
				change = true;
			}
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
