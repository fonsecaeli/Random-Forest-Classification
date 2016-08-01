package com.gui.gfx;
public class Button extends Interactable {
	private String name;
	
	
	public Button(int x, int y, int width, int height, String name){
		super(x, y, width, height);
		this.name = name;
	}
	
	public final String getName(){
		return name;
	}
	
	public void onClick(MouseEvent me){
		
	}
	
	public final void render(Screen screen){
		
	}
}
