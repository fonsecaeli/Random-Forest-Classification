package com.main;

import com.gui.gfx.Screen;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
            Screen screen = new Screen(640,640);
            screen.render();
            screen.render();
            //For Ben: ImportData.importData("C:\\Users\\Benjamin 2\\Documents\\NetBeansProjects\\RandomForestClassification\\RandomForestClassification\\src\\tic_tac_toeData.csv");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Input your file path here and click enter: ");
            String input = scanner.nextLine();
            input = input.replace("\'", "").replace("\"", "");
            DataSet ds = ImportData.importData(input);
            DecisionTree tree = new DecisionTree(ds);
            System.out.println(tree);
            
	}
}
