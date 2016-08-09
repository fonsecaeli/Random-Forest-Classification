package com.gui.interactable;

import com.gui.gfx.Screen;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class Interactable {
	private int x, y, //The relative to the screen. They are relative to the Interactable that contains it.
                    width, height; //the width/height of the hitbox and image
        private List<Interactable> interactables; //A list of interactables which are updated when this
	private BufferedImage image; //The image this interactable draws, has width of width and height of height
	
        /**
         * The basic constructor, initializes the contained interactables, and then creates the image
         * @param x1 the relative x-coord of the interactable
         * @param y1 the relative x-coord of the interactable
         * @param w the width of the hitbox of the interactable
         * @param h  the height of the hitboxinteractable
         */
	public Interactable(int x1, int y1, int w, int h){
            x = x1;
            y = y1;
            width = w;
            height = h;
            interactables = new ArrayList<>();
            createImage();
	}
        
        /**
         * creates image with the stored dimensions
         */
        private final void createImage(){
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        }
        
        public BufferedImage getImage(){
            return image;
        }
        
        public void setImage(BufferedImage bi){
            image = bi;
            setWidth(bi.getWidth());
            setHeight(bi.getHeight());
        }
	
        /**
         * @param me the mouse event itself, used for its coords
         * @param xoff the x offset of the mouse event, global coords on screen
         * @param yoff the y offset of the mouse event, global coords on screen
         * @returns whether the given mouse event intersects the object
         */
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
        
        public final List<Interactable> getInteractables(){
            return interactables;
        }
	
        /**
         * clicked - brief press then release
         */
	public void mouseClicked(MouseEvent me, int xoff, int yoff){
            for(Interactable i : interactables){
                if(i.contains(me, getX()+xoff, getY()+yoff)){
                    i.mouseClicked(me, getX()+xoff, getY()+yoff);
                }
            }
        }
        
        /**
         * pressed - mouse is pressed down
         */
	public void mousePressed(MouseEvent me, int xoff, int yoff){
            for(Interactable i : interactables){
                if(i.contains(me, getX()+xoff, getY()+yoff)){
                    i.mousePressed(me, getX()+xoff, getY()+yoff);
                }
            }
        }
        
        /**
         * released - mouse is released
         */
	public void mouseReleased(MouseEvent me, int xoff, int yoff){
            for(Interactable i : interactables){
                if(i.contains(me, getX()+xoff, getY()+yoff)){
                    i.mouseReleased(me, getX()+xoff, getY()+yoff);
                } else {
                    i.mouseNotHovered(me, getX()+xoff, getY()+yoff);
                }
            }
        }
        
        
        /**
         * not hovered - mouse event does not intersect and mouse is moving
         */
	public void mouseNotHovered(MouseEvent me, int xoff, int yoff){
            for(Interactable i : interactables){
                if(!i.contains(me, getX()+xoff, getY()+yoff)){
                    i.mouseNotHovered(me, getX()+xoff, getY()+yoff);
                }
            }
        }
        
        /**
         * hovered - mouse event intersects and mouse is moving
         */
	public void mouseHovered(MouseEvent me, int xoff, int yoff){
            for(Interactable i : interactables){
                if(i.contains(me, getX()+xoff, getY()+yoff)){
                    i.mouseHovered(me, getX()+xoff, getY()+yoff);
                } else {
                    i.mouseNotHovered(me, getX()+xoff, getY()+yoff);
                }
            }
        }
        
        /**
         * dragged - mouse event intersects, mouse is pressed, and mouse is moving
         */
	public void mouseDragged(MouseEvent me, int xoff, int yoff){
            for(Interactable i : interactables){
                if(i.contains(me, getX()+xoff, getY()+yoff)){
                    i.mouseDragged(me, getX()+xoff, getY()+yoff);
                } else {
                    i.mouseNotHovered(me, getX()+xoff, getY()+yoff);
                }
            }
        }
        
        /**
         * draws image, then draws all of its interactables
         */
        public void render(int xoff, int yoff, Screen screen){
            screen.drawImage(image, getX()+xoff, getY()+yoff);
            for(Interactable i : interactables){
                i.render(getX()+xoff, getY()+yoff, screen);
            }
        }
}
