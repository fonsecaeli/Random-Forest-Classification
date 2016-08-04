package com.main;

public class Main {

	public static void main(String[] args) {
  /*          Scanner scanner = new Scanner(System.in);
            //System.out.println("Input the font file: ");
            //String fontInput = scanner.nextLine();
            //fontInput = fontInput.replace("\'", "").replace("\"", "");
            ImageHandler ih = new ImageHandler("/font.png");
            Font.init(8,14);
            
            Screen screen = new Screen(1280,700);
            GUI gui = new GUI(screen);
            screen.setInput(gui);
            screen.render();
            screen.render();
            screen.render();
            screen.render();
            screen.render();
            //For Ben: ImportData.importData("C:\\Users\\Benjamin 2\\Documents\\NetBeansProjects\\RandomForestClassification\\RandomForestClassification\\src\\tic_tac_toeData.csv");
            *//*System.out.print("Input your file path here and click enter: ");
            String input = scanner.nextLine();
            input = input.replace("\'", "").replace("\"", "");
            DataSet ds = ImportData.importData(input);
            DecisionTree tree = new DecisionTree(ds);
            System.out.println(tree);*//*
            *//*SideBar sb = new SideBar(screen, 1000, 0);
            sb.render(screen);*//*
            gui.render(screen);
            screen.render();
            screen.render();
            screen.render();
            screen.render();
            screen.render();
            screen.render();
            screen.render();
            screen.render();
            screen.render();
            screen.render();
            screen.render();
            screen.render();
            screen.render();*/

        DataSet d = ImportData.importData("C:\\Users\\EliFo\\OneDrive - Lakeside School\\Projects\\MachineLearningProject\\Random-Forest-Classification\\src\\golfData.csv");
        RandomForest f = new RandomForest(d, 1, 1);
        System.out.println(f.oob());
        System.out.println(f);
    }
}
