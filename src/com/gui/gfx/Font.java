
package com.gui.gfx;

import com.gui.input.ImageHandler;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class Font {
    private static final int TAB_SIZE=2;
    private static int charWidth, charHeight;
    private static BufferedImage fontImage;
    
    public static final void init(int l, int h){
        charWidth = l;
        charHeight = h;
        
        fontImage = ImageHandler.fontImage;
        
    } 
    
    public static BufferedImage stringToBufferedImage(String s){
        if(s.equals("")){
            return null;
        }
        
        int numLines = getNumber(s, "\n")+1;
        int longestSubstring = longestLengthBetweenString(s, "\n");
        
        BufferedImage bi = new BufferedImage(longestSubstring*charWidth, charHeight*numLines, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.getGraphics();
        int xToDraw=0, yToDraw=0;
        for(int i=0; i<s.length(); i++){
            int asciiCode = (int)s.charAt(i);
            if(!(asciiCode>=0&&asciiCode<=255)){
                BufferedImage charImage = fontImage.getSubimage(charWidth*(0),
                                                                charHeight*(0),
                                                                charWidth,
                                                                charHeight);
                g.drawImage(charImage, xToDraw, yToDraw, null);
                xToDraw+=charWidth;
            } else if(asciiCode==10){
                xToDraw=0;
                yToDraw+=charHeight;
            } else if(asciiCode==9){
                for(int tabSpaces=0; tabSpaces<TAB_SIZE; tabSpaces++){
                    BufferedImage charImage = fontImage.getSubimage(charWidth*(((int)' ')%32),
                                                                    charHeight*(((int)' ')/32),
                                                                    charWidth,
                                                                    charHeight);
                    g.drawImage(charImage, xToDraw, yToDraw, null);
                    xToDraw+=charWidth;
                }
            } else {
                BufferedImage charImage = fontImage.getSubimage(charWidth*(asciiCode%32),
                                                                charHeight*(asciiCode/32),
                                                                charWidth,
                                                                charHeight);
                g.drawImage(charImage, xToDraw, yToDraw, null);
                xToDraw+=charWidth;
            }
        }
        /*System.out.println();
        System.out.println("BufferedImage: "+bi);
        if(bi==null)System.out.println("************************************************************");
        System.out.println("String: "+s);
        System.out.println("numLines: "+numLines);
        System.out.println("longestSubstring: "+longestSubstring);*/
        
        return bi;
    }
    
    
    public static List<BufferedImage> stringToBufferedImageList(String s){
        if(s.equals("")){
            return new ArrayList<>();
        }
        
        List<BufferedImage> bis = new ArrayList<>();
        
        String[] strings = s.split("\n");
        for(int i=0; i<strings.length; i++){
            if(!strings[i].equals("")){
                bis.add(stringToBufferedImage(strings[i]));
            }
        }
        return bis;
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
    
    private static int getNumber(String container, String check){
        return container.length() - container.replace(check,"").length();
    }
    
    private static int longestLengthBetweenString(String container, String check){
        String[] strings = container.split(check);
        int maxSize = 0;
        for(int i=0;i<strings.length; i++){
            int stringLength = stringLength(strings[i]);
            if(stringLength>maxSize){
                maxSize=stringLength;
            }
        }
        
        return maxSize;
    }
    
    //no \n chars allowed
    public static int stringLength(String s){
        return s.length()+(TAB_SIZE-1)*getNumber(s,"\t")/*-getNumber(s,"\n")*/;
    }
    
    public static int stringWidth(String s){
        return longestLengthBetweenString(s,"\n")*charWidth;
    }
    
    public static int stringHeight(String s){
        return (1+getNumber(s,"\n"))*charHeight;
    }
}
