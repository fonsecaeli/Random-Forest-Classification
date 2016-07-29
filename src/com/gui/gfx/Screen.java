
package com.gui.gfx;

import com.gui.input.KeyboardInputListener;
import com.gui.input.MouseInputListener;
import com.gui.input.MouseMotionInputListener;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Screen extends Canvas {
    public int WIDTH = 512;
    public int HEIGHT = 512;
    public final String TITLE = "Test";
    //offsets
    int xoff=0,yoff=0;
    
    //inputs
    public MouseInputListener mouse = new MouseInputListener();
    public MouseMotionInputListener mouseMovement = new MouseMotionInputListener();
    public KeyboardInputListener keyboard = new KeyboardInputListener();
    
    
    
    public Screen(int width, int height){
        WIDTH=width;
        HEIGHT=height;

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
    
    public void init() {
        requestFocus();

        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouseMovement);
        this.addKeyListener(keyboard);
    }
    
    public void setXOff(int i){
        xoff+=i;
    }
    
    public void setYOff(int i){
        yoff+=i;
    }
    
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        
        if (bs==null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        
        g.setColor(new Color(0,0,0));
        g.fillRect(0,0,getWidth(),getHeight());

        
        g.dispose();
        bs.show();
    }

    /**
     * used to set offset
     */
    /*public void tick(){
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
    }*/
}
