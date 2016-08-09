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
	//Background color for the FishEye window
	private static final Color BACKGROUND_COLOR = Color.WHITE;
	//ChildSpacind, the amount of pixels between the child nodes (bottom set of nodes)
	private final int CHILD_SPACING = 20;
	//Vertical spacing between node generations as a factor of the entire window, Middle height from the top as a factor of the entire window
	private final double VERTICAL_SPACING = 0.2, MIDDLE_HEIGHT = 0.5;
        
	//These are all of the different stacks that are used to store all of the nodes
	ArrayList <Stack<Node>> stacks;
	//These are all of the different trees which correspond to their respective stacks
	DecisionTree[] trees;
	//The current index of the tree within stacks and trees
	private int curIndex;
	//The top button in the tree
	private FishEyeButton top;
	//The central node in the tree
	private Button middle;
	//All the child nodes in the tree
	private ArrayList<FishEyeButton> bottom;
	//this flag is true if a change happened so that the render class will update everything and then set it to false
	private boolean change;
	
	/**
	* This is the constructor for the object, it stores its coordinates for the window and initializes everything
	*/
	public FishEye(int x, int y, int width, int height){
		super(x, y, width, height);
		initImage();
		refreshData();
	}
	
	/**
	* Initializes a background
	*/
	private void initImage(){
		Graphics g = getImage().getGraphics();
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	/**
	* Makes sure the buffered image is up to date along with all the interactables stored in this object
	*/
        @Override
	public void render(int xOff, int yOff, Screen screen){
		refreshData();
		super.render(xOff, yOff, screen);
	}
	
	/**
	* Updates all of the data and redraws all of the buttons if there is a change
	*/
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
	
	/**
	* refreshes the entire image and changes the change boolean to false
	*/
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
	
	/**
	* Refreshes the top button in the tree
	*/
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
	
	/**
	* Refreshes the middle button in the tree
	*/
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
	
	/**
	* Refreshes the child node buttons in the tree
	*/
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
	
	/**
	* Draws all of the lines so that the buttons can be drawn over them later
	*/
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
	
	/**
	* Returns true if a node is a leaf node
	*/
	private boolean isLeafNode(Node node){
		return (node.getDecision()!=null);
	}
	
	/**
	* This method gets called when one of the buttons gets pressed and handles the button number accordingly
	*/
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
	
	/**
	* This is a button used only by fishEye that basically has some extra methods that it needs to operate
	*/
	public class FishEyeButton extends Button{
		//dad is the object that contains this button object
		private FishEye dad;
		//index is -1 if it is the top button and 0+ if it is a child button
		private int index;
		//This is the key for the corresponding node within the tree
		private String key;
		
		/**
		* This constructor makes the interactable using the input text and stores all the other data in the variables
		*/
		public FishEyeButton(int x, int y, FishEye dad, int index, String text, String key){
			super(x, y, text);
			this.dad = dad;
			this.index = index;
			this.key = key;
		}
		
		/**
		* Pretty self explainatory
		*/
		public String getKey(){
			return key;
		}
		
		/**
		* This is what calls clicked and gives it the index of the button stored within the object
		*/
                @Override
		public void onAction(MouseEvent me){
			dad.clicked(index);
		}
	}
}
