package com.gui.gfx;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class Interactable {

	private int x, y, width, height;
    private List<Interactable> interactables;
	private BufferedImage image;
	
	public Interactable(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
                interactables = new ArrayList<>();
                initImage(width, height);
	}
        
        public void initImage(int width, int height){
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        }
        
        public BufferedImage getImage(){
            return image;
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
        
        public void addInteractable(Interactable i){
            interactables.add(i);
        }
        
        public void removeInteractable(Interactable i){
            interactables.remove(i);
        }
	
	public void mouseClicked(MouseEvent me){
            for(Interactable i : interactables){
                if(i.contains(me)){
                    i.mouseClicked(me);
                }
            }
        }
        
	public void mouseReleased(MouseEvent me){
            for(Interactable i : interactables){
                if(i.contains(me)){
                    i.mouseReleased(me);
                }
            }
        }
        
	public void mouseMoved(MouseEvent me){
            for(Interactable i : interactables){
                if(i.contains(me)){
                    i.mouseMoved(me);
                }
            }
        }
        
	public void mouseDragged(MouseEvent me){
            for(Interactable i : interactables){
                if(i.contains(me)){
                    i.mouseDragged(me);
                }
            }
        }
        
        public void render(Screen screen){
            screen.drawImage(image, getX(), getY());
            for(Interactable i : interactables){
                i.render(screen);
            }
        }
}
