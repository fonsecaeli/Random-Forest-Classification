
package com.gui.gfx;

import com.gui.input.ImageHandler;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Font {
    private static int charWidth, charHeight;
    private static BufferedImage fontImage;
    
    public static final void init(int l, int h, String filePath){
        charWidth = l;
        charHeight = h;
        
        ImageHandler ih = new ImageHandler(filePath);
        fontImage = ih.getImage();
        
    }
    
    public static BufferedImage stringToBufferedImage(String s){
        BufferedImage bi = new BufferedImage(s.length()*charWidth, charHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.getGraphics();
        for(int i=0; i<s.length(); i++){
            int asciiCode = (int)s.charAt(i);
            BufferedImage charImage = fontImage.getSubimage(charWidth*(asciiCode%32),
                                                            charHeight*(asciiCode/32),
                                                            charWidth,
                                                            charHeight);
            g.drawImage(charImage, i*charWidth, 0, null);
        }
        
        return bi;
    }
    
    public static int getCharWidth() {
        return charWidth;
    }

    public static int getCharHeight() {
        return charHeight;
    }

    public static BufferedImage getFontImage() {
        return fontImage;
    }
}
