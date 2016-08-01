package com.gui.input;


import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageHandler {
    public static BufferedImage fontImage;
    
    public ImageHandler(){
        try{
            fontImage = loadImage("/font.png");
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private BufferedImage loadImage(String path) throws IOException{
        BufferedImage image = ImageIO.read(getClass().getResource(path));
        return image;
    }
}
