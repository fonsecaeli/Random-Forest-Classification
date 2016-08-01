
package com.gui.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Font {
    private static int charWidth, charHeight;
    private static BufferedImage fontImage;
    
    public Font(int l, int h, String filePath){
        charWidth = l;
        charHeight = h;
        
        try{
            fontImage = loadImage(filePath);
        } catch(IOException e){
            e.printStackTrace();
        }
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
    
    private BufferedImage loadImage(String path) throws IOException{
        BufferedImage image = ImageIO.read(getClass().getResource(path));
        return image;
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
