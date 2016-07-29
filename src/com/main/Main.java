package com.main;

import java.util.Scanner;
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
            DataSet ds = ImportData.importData(new Scanner(System.in).nextLine());
            DecisionTree tree = new DecisionTree(ds);
            System.out.println(tree);
	}
}
