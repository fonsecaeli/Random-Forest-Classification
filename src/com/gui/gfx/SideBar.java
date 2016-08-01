package com.gui.gfx;

public class SideBar {
	private final Button LOAD, SAVEAS;
	private int xOffSet, yOffSet = 0;
	
	public SideBar(){
		LOAD = new LoadButton();
	}
	
	
	
	
	
	
	
	
	private class LoadButton extends Button {
		public LoadButton(String name){
			super();
		}
	}
}
