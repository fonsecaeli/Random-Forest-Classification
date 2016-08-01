package com.gui.input;


import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageHandler {
    private BufferedImage image;
    
    public ImageHandler(String filePath){
        try{
            image = loadImage(filePath);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public BufferedImage getImage(){
        return image;
    }
    
    private BufferedImage loadImage(String path) throws IOException{
        BufferedImage image = ImageIO.read(getClass().getResource(path));
        return image;
    }
}
