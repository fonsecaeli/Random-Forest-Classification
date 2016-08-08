package com.gui.interactable;

import java.awt.Color;
import com.gui.gfx.Screen;
import com.main.DecisionTree;
import com.main.Node;
import com.main.StaticStorage;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;

public class FishEye extends Interactable {
	private static final Color BACKGROUND_COLOR = Color.WHITE;
	private final int CHILD_SPACING = 20;
	private final double VERTICAL_SPACING = 0.2, MIDDLE_HEIGHT = 0.5;
        
	ArrayList <Stack<Node>> stacks;
	DecisionTree[] trees;
	private int curIndex;
	private FishEyeButton top;
	private Button middle;
	private ArrayList<FishEyeButton> bottom;
	private boolean change;
	
	public FishEye(int x, int y, int width, int height){
		super(x, y, width, height);
		initImage();
		init();
	}
	
	private void initImage(){
		Graphics g = getImage().getGraphics();
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	private void init(){
		if (StaticStorage.getCurrentRandomForest()!=null){
			trees = StaticStorage.getCurrentRandomForest().getTrees();
			stacks = new ArrayList<>(trees.length);
			change = false; curIndex = StaticStorage.getIndexOfCurrentTree();
		}
	}
	
        @Override
	public void render(int xOff, int yOff, Screen screen){
		refreshData();
		super.render(xOff, yOff, screen);
	}
	
	private void refreshData(){
		if (StaticStorage.getCurrentRandomForest()!=null&&
			StaticStorage.getCurrentRandomForest().getTrees()!=trees){
			trees = StaticStorage.getCurrentRandomForest().getTrees();
			stacks = new ArrayList<>();
			for (int i=0; i<trees.length; i++)
				stacks.add(new Stack<>());
			change = true;
		}
		if (curIndex!=StaticStorage.getIndexOfCurrentTree()) change = true;
		if (StaticStorage.getCurrentRandomForest()!=null&&change) refreshImage();
	}
	
	public void refreshImage(){
		curIndex = StaticStorage.getIndexOfCurrentTree();
		
		//pushes the head node to the stack if it isn't there yet
		if (stacks.get(curIndex).empty()){
			stacks.get(curIndex).push(trees[curIndex].getHeadNode());
		}
		
		refreshTop();
		refreshMiddle();
		refreshChildren();
		
		initImage();
		renderLines();
		change = false;
	}
	
	private void refreshTop(){
		//makes the button above the current node if the node on the stack isn't the head node and removes the button if the head node is alone on the stack
		if (stacks.get(curIndex).size()>=2){
			Node temp = stacks.get(curIndex).pop();
			if (top!=null) removeInteractable(top);
			String name = "("+stacks.get(curIndex).peek().getKeyString()+") "+stacks.get(curIndex).peek().getAttribute().getName();
			top = new FishEyeButton((getWidth()-Button.getWidth(name))/2 , 
						(int)((MIDDLE_HEIGHT-VERTICAL_SPACING)*(getHeight()-Button.getHeight(name))),
						this, -1, name, name);
			addInteractable(top);
			stacks.get(curIndex).push(temp);
		} else {
			removeInteractable(top);
			top = null;
		}
	}
	
	private void refreshMiddle(){
		//'remakes' the middle button
		Node node = stacks.get(curIndex).peek();
		if (middle!=null) removeInteractable(middle);
		String name;
		if (node.getKeys().size()>0)
			name = "("+node.getKeyString()+") "+node.getAttribute().getName();
		else name = "("+node.getKeyString()+") "+node.getDecision();
		middle = new Button((getWidth()-Button.getWidth(name))/2,
					(int)(MIDDLE_HEIGHT*(getHeight()-Button.getHeight(name))), name);
		addInteractable(middle);
	}
	
	
	
	private void refreshChildren(){
		//removes all of the child nodes so they can be 'remade'
		if (bottom!=null)
			for (FishEyeButton f: bottom)
				removeInteractable(f);
		bottom = new ArrayList<>();
		Node node = stacks.get(curIndex).peek();
		
		//counts width of all the child buttons together
		int sum = 0;
		for (String a: node.getKeys()){
			if (isLeafNode(node.getChild(a))) sum+=Button.getWidth(a+": "+node.getChild(a).getDecision())+CHILD_SPACING;
			else sum+=Button.getWidth(a)+CHILD_SPACING;
		}
		sum-=CHILD_SPACING;
		sum = (getWidth()-sum)/2;
		int i=0;
		
		//makes all of the child buttons
		for (String a: node.getKeys()){
			if (isLeafNode(node.getChild(a))) {
				bottom.add(new FishEyeButton(sum, (int)((MIDDLE_HEIGHT+VERTICAL_SPACING)*(getHeight()-Button.getHeight(a))), this, i, a+": "+node.getChild(a).getDecision(), a));
				sum+=Button.getWidth(a+": "+node.getChild(a).getDecision())+CHILD_SPACING;
			}
			else {
				bottom.add(new FishEyeButton(sum, (int)((MIDDLE_HEIGHT+VERTICAL_SPACING)*(getHeight()-Button.getHeight(a))), this, i, a, a));
				sum+=Button.getWidth(a)+CHILD_SPACING;
			}
			addInteractable(bottom.get(bottom.size()-1));
			i++;
		}
	}
	
	private void renderLines(){
		if (middle!=null){
			Graphics g = getImage().getGraphics();
			g.setColor(Color.BLACK);
			if (top!=null)
				g.drawLine(top.getX()+(top.getWidth()/2), top.getY()+(top.getHeight()/2),
					middle.getX()+(middle.getWidth()/2), middle.getY()+(middle.getHeight()/2));
			
			if (bottom!=null&&bottom.size()>0)
				for(FishEyeButton f: bottom)
					g.drawLine(middle.getX()+(middle.getWidth()/2), middle.getY()+(middle.getHeight()/2), f.getX()+(f.getWidth()/2), f.getY()+(f.getHeight()/2));
		}
	}
	
	private boolean isLeafNode(Node node){
		return (node.getDecision()!=null);
	}
	
	public void clicked(int index){
		if (index<0){
			stacks.get(curIndex).pop();
			change = true;
		} else if (index>=0&&index<bottom.size()){
			if (!isLeafNode(stacks.get(curIndex).peek().getChild(bottom.get(index).getKey()))){
				stacks.get(curIndex).push(stacks.get(curIndex).peek().getChild(bottom.get(index).getName()));
				change = true;
			}
		}
	}
	
	public class FishEyeButton extends Button{
		private FishEye dad;
		private int index;
		private String key;
		public FishEyeButton(int x, int y, FishEye dad, int index, String text, String key){
			super(x, y, text);
			this.dad = dad;
			this.index = index;
			this.key = key;
		}
		
		public String getKey(){
			return key;
		}
		
                @Override
		public void onAction(MouseEvent me){
			dad.clicked(index);
		}
	}
}
