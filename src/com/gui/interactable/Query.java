package com.gui.interactable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import com.gui.gfx.Screen;
import com.main.StaticStorage;


public class Query extends Interactable {
	private static final Color BACKGROUND_COLOR = Color.WHITE;
	
	public Query(int x, int y, int width, int height){
		super(x, y, width, height);
		initImage();
		addInteractable(new QueryButton(0, 0));
	}
	
	private void initImage(){
		Graphics g = getImage().getGraphics();
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	public class QueryButton extends Button {
		
		
		public QueryButton(int x, int y){
			super(x, y, "Query...");
		}
		
		@Override
		public void onAction(MouseEvent me){
			
		}
	}
}
