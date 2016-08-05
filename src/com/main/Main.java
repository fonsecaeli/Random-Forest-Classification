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
            new ImageHandler("/font.png");
            Font.init(8,14);
            
            Screen screen = new Screen(1280,700);
            GUI gui = new GUI(screen);
            screen.setInput(gui);
		while(true) {
			gui.render(0, 0, screen);
			screen.render();
		}
        /*DataSet d = ImportData.importData("C:\\Users\\EliFo\\OneDrive - Lakeside School\\Projects\\MachineLearningProject\\Random-Forest-Classification\\src\\golfData.csv");
        RandomForest r = new RandomForest(d, 10, 1);
        System.out.println(r);*/
    }
}
