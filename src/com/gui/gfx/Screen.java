
package com.gui.gfx;

import project.world.tile.Tile;
import project.input.KeyboardInputListener;
import project.input.MouseInputListener;
import project.input.MouseMotionInputListener;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;
//import project.world.WorldHandler;

public class Screen extends Canvas {
    public int WIDTH = 512;
    public int HEIGHT = 512;
    public final String TITLE = "Test";
    //what's edited and drawn to the screen
    private BufferedImage draw;
    // the BufferedImage's byte array
    private int[] pixels;
    //offsets
    int xoff=0,yoff=0;
    
    //inputs
    public MouseInputListener mouse = new MouseInputListener();
    public MouseMotionInputListener mouseMovement = new MouseMotionInputListener();
    public KeyboardInputListener keyboard = new KeyboardInputListener();
    
    
    
    public Screen(int width, int height){
        WIDTH=width;
        HEIGHT=height;
        draw = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        pixels = ((DataBufferInt) draw.getRaster().getDataBuffer()).getData();
        
        
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setMaximumSize(new Dimension(WIDTH,HEIGHT));
        setMinimumSize(new Dimension(WIDTH,HEIGHT));
        
        JFrame frame = new JFrame(TITLE);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public void init(){
        requestFocus();
        
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouseMovement);
        this.addKeyListener(keyboard);
    }
    
    public void drawTile(int x, int y, Tile spr){
        //check if sprite will be drawn | if the drawing will make any difference
        if((x-xoff>0 || x-xoff+spr.getWidth()<WIDTH || y-yoff>0 || y-yoff+spr.getHeight()<HEIGHT)){
            for(int i=x-xoff;i<x+spr.getWidth()-xoff;i++){
                for(int j=y-yoff;j<y+spr.getHeight()-yoff;j++){
                    //check if the pixel is in bounds
                    if(i>=0 && i<WIDTH && j>=0 && j<HEIGHT){
                        if(spr.getOpacity((i-x+xoff)+(j-y+yoff)*spr.getWidth())!=0){
                            //pixels[(i+j*WIDTH)]=spr.getPixel((i-x+xoff)+(j-y+yoff)*spr.getWidth());
                            draw.setRGB(i, j, spr.getPixel((i-x+xoff)+(j-y+yoff)*spr.getWidth()));
                        }
                    }
                }
            }
        }
    }
    
    public void drawPixel(int x, int y, int value){
        //check if sprite will be drawn | if the drawing will make any difference
        if((x-xoff>0 || x-xoff<WIDTH || y-yoff>0 || y-yoff<HEIGHT)){
                pixels[(x-xoff+(y-yoff)*WIDTH)]=value;
        }
    }
    
    public void setXOff(int i){
        xoff+=i;
    }
    
    public void setYOff(int i){
        yoff+=i;
    }
    
    public void render(){
        //WorldHandler.render(this);
        BufferStrategy bs = this.getBufferStrategy();
        
        if (bs==null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        
        g.setColor(new Color(0,0,0));
        g.fillRect(0,0,getWidth(),getHeight());
        g.drawImage(draw, 0, 0, null);
        
        g.dispose();
        bs.show();
    }
    
    public void tick(){
        int movementSpeed = 8;
        if(keyboard.right){
            setXOff(movementSpeed);
        }
        if(keyboard.left){
            setXOff(-movementSpeed);
        }
        if(keyboard.up){
            setYOff(-movementSpeed);
        }
        if(keyboard.down){
            setYOff(movementSpeed);
        }
        
        //WorldHandler.tick();
        
    }
}
