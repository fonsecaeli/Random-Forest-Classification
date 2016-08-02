package com.gui.gfx;

import java.awt.event.MouseEvent;

public abstract class Interactable {
	private int x, y, width, height;
	
	public Interactable(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public final boolean contains(MouseEvent me){
		return ((me.getX()>x && me.getX()<x+width) && (me.getY()>y && me.getY()<x+height));
	}
	
	public final int getX(){
		return x;
	}
	
	public final int getY(){
		return y;
	}
	
	public final int getWidth(){
		return width;
	}
	
	public final int getHeight(){
		return height;
	}
	
	public abstract void onClick(MouseEvent me);
	public abstract void onHover(MouseEvent me);
	public abstract void onDrag(MouseEvent me);
}
