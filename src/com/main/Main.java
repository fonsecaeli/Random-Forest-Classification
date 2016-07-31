package com.main;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
        //System.out.println(ds);
        //System.out.println(Entropy.entropy(ds));
        /*
        List<Attribute> atts = ds.getAttributes();
        for(int i=0; i<atts.size(); i++){
            System.out.println(atts.get(i).getName()+": ");
            double attEn = Entropy.attributeEntropy(ds, atts.get(i));
            System.out.println("Attribute Entropy: "+attEn);
            System.out.println();
        }*/

        /*
        System.out.println("\n\n\n");

        ds = ImportData.importData("C:\\Users\\Benjamin 2\\Documents\\NetBeansProjects\\RandomForestClassification\\RandomForestClassification\\src\\irisData.csv");
        System.out.println(ds);
        System.out.println(Entropy.entropy(ds));

        atts = ds.getAttributes();
        for(int i=0; i<atts.size(); i++){
            System.out.println(atts.get(i).getName()+": "+Entropy.attributeEntropy(ds, atts.get(i)));
        }
        */
        System.out.println("Enter training Data: ");
        DataSet ds = ImportData.importData("C:\\Users\\EliFo\\OneDrive - Lakeside School\\Projects\\MachineLearningProject\\Random-Forest-Classification\\src\\tic_tac_toeData.csv");
        System.out.println("Enter testing Data: ");
        DataSet test = ImportData.importData("C:\\Users\\EliFo\\OneDrive - Lakeside School\\Projects\\MachineLearningProject\\Random-Forest-Classification\\src\\tic_tac_toe_TestData.csv", new ArrayList(ds.getAttributes()));

        RandomForest forest = new RandomForest(ds, 100);

        //DecisionTree tree = new DecisionTree(ds);

        for (Record r: test.getRecords()) {
            System.out.println(forest.queryTrees(r));
        }
        System.out.println(forest.getAverageAccuracy());
        System.out.println(Arrays.toString(forest.getTreesAccuracy()));
    }
}
