package com.gui.interactable;

import java.util.Stack;

public class FishEye extends Interactable {
	Stack[] stack;
	DecisionTree[] trees;
	int curIndex;
	
	public FishEye(int x, int y, int width, int height){
		super(x, y, width, height);
		trees = StaticStorage.getCurrentRandomForest().getTrees();
		stack = newStack[trees.length];
		
	}
}
