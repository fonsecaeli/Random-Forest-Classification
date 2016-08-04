package com.gui.interactable;

import com.gui.gfx.Screen;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class Interactable {
	private int x, y, width, height;
        private List<Interactable> interactables;
	private BufferedImage image;
	
	public Interactable(int x1, int y1, int w, int h){
            x = x1;
            y = y1;
            width = w;
            height = h;
            interactables = new ArrayList<>();
            initImage(width, height);
	}
        
        public void initImage(int width, int height){
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        }
        
        public BufferedImage getImage(){
            return image;
        }
	
	public final boolean contains(MouseEvent me, int xoff, int yoff){
            int testX = x+xoff;
            int testY = y+yoff;
            return ((me.getX()>=testX && me.getX()<=testX+width) && (me.getY()>=testY && me.getY()<=testY+height));
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
        
	
	public final void setX(int x1){
		x = x1;
	}
	
	public final void setY(int y1){
		y = y1;
	}
	
	public final void setWidth(int w){
		width = w;
	}
	
	public final void setHeight(int h){
		height = h;
	}
        
        protected void addInteractable(Interactable i){
            interactables.add(i);
        }
        
        protected void removeInteractable(Interactable i){
            interactables.remove(i);
        }
	
	public void mouseClicked(MouseEvent me, int xoff, int yoff){
            for(Interactable i : interactables){
                if(i.contains(me, getX()+xoff, getY()+yoff)){
                    i.mouseClicked(me, getX()+xoff, getY()+yoff);
                }
            }
        }
        
	public void mousePressed(MouseEvent me, int xoff, int yoff){
            for(Interactable i : interactables){
                if(i.contains(me, getX()+xoff, getY()+yoff)){
                    i.mousePressed(me, getX()+xoff, getY()+yoff);
                }
            }
        }
        
	public void mouseReleased(MouseEvent me, int xoff, int yoff){
            for(Interactable i : interactables){
                if(i.contains(me, getX()+xoff, getY()+yoff)){
                    i.mouseReleased(me, getX()+xoff, getY()+yoff);
                } else {
                    i.mouseNotHovered(me, getX()+xoff, getY()+yoff);
                }
            }
        }
        
	public void mouseNotHovered(MouseEvent me, int xoff, int yoff){
            for(Interactable i : interactables){
                if(!i.contains(me, getX()+xoff, getY()+yoff)){
                    i.mouseNotHovered(me, getX()+xoff, getY()+yoff);
                }
            }
        }
        
	public void mouseHovered(MouseEvent me, int xoff, int yoff){
            for(Interactable i : interactables){
                if(i.contains(me, getX()+xoff, getY()+yoff)){
                    i.mouseHovered(me, getX()+xoff, getY()+yoff);
                } else {
                    i.mouseNotHovered(me, getX()+xoff, getY()+yoff);
                }
            }
        }
        
	public void mouseDragged(MouseEvent me, int xoff, int yoff){
            for(Interactable i : interactables){
                if(i.contains(me, getX()+xoff, getY()+yoff)){
                    i.mouseDragged(me, getX()+xoff, getY()+yoff);
                } else {
                    i.mouseNotHovered(me, getX()+xoff, getY()+yoff);
                }
            }
        }
        
        public void render(int xoff, int yoff, Screen screen){
            screen.drawImage(image, getX()+xoff, getY()+yoff);
            for(Interactable i : interactables){
                i.render(getX()+xoff, getY()+yoff, screen);
            }
        }
}
