package com.main;

import com.gui.gfx.Font;
import com.gui.gfx.Screen;
import com.gui.input.ImageHandler;
import com.gui.interactable.GUI;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Input the font file: ");
        //String fontInput = scanner.nextLine();
        //fontInput = fontInput.replace("\'", "").replace("\"", "");
        new ImageHandler("/font2x.png");
        Font.init(16,28);

        Screen screen = new Screen(1920,1020);
        GUI gui = new GUI(screen);
        screen.setInput(gui);

        long lastTime = System.nanoTime();
        final double amountOfTicks = 120.0;
        double ns = 1000000000/amountOfTicks;
        double delta=0;
        //int updates=0;
        int frames=0;
        long timer = System.nanoTime();

        while (true){
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;
            if (delta>=1){
                {//render
                gui.render(0, 0, screen);
                screen.render();
                }
                //tick();
                //updates++;
                frames++;
                delta--;
            }

            if (System.nanoTime()-timer>1000000000){
                timer+=1000000000;
                System.out.println(/*updates + " Ticks, */"FPS "+frames);
                //updates=0;
                frames=0;
            }
        }
       /* DataSet d = ImportData.importData("C:\\Users\\EliFo\\OneDrive - Lakeside School\\Projects\\MachineLearningProject\\Random-Forest-Classification\\src\\golfData.csv");
        RandomForest r = new RandomForest(d, 10, 1);*/
        //System.out.println(r);
    }
}
