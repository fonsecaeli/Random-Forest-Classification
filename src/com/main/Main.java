package com.main;

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
        /*System.out.println("Enter training Data: ");
        DataSet ds = ImportData.importData("C:\\Users\\EliFo\\OneDrive - Lakeside School\\Projects\\MachineLearningProject\\Random-Forest-Classification\\src\\tic_tac_toeData.csv");
        System.out.println("Enter testing Data: ");
        DataSet test = ImportData.importData("C:\\Users\\EliFo\\OneDrive - Lakeside School\\Projects\\MachineLearningProject\\Random-Forest-Classification\\src\\tic_tac_toe_TestData.csv", new ArrayList(ds.getAttributes()));
*/
        DataSet ds = ImportData.importData("C:\\Users\\EliFo\\OneDrive - Lakeside School\\Projects\\MachineLearningProject\\Random-Forest-Classification\\src\\tic_tac_toeData.csv");


        /*int numAtts = ds.getAttributes().size();
        double largestFactor = numAtts/Math.sqrt(numAtts);
        //System.out.println(largestFactor);

        long start = System.currentTimeMillis();
        RandomForest bestForest = null;
        double bestOob = 1;
        double tuningFactor = 0;
        for(int i = 1; i <= (largestFactor/.5); i++) {
            RandomForest forest = new RandomForest(ds, 100, .5*i);
            if(forest.oob() < bestOob) {
                bestOob = forest.oob();
                bestForest = forest;
                tuningFactor = i*.5;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Best oob: "+bestOob);
        System.out.println("Best tuning factor: "+tuningFactor);
        System.out.println("Time to tune: "+(end-start)/1000);*/

        RandomForest bestForest = new RandomForest(ds, 10, 3);
        System.out.println("forests oob: "+bestForest.oob());

       // System.out.println(bestForest);

        /*for (Record r: test.getRecords()) {
            System.out.println(forest.queryTrees(r));
        }*/
        //System.out.println(bestForest.oob());
        //System.out.println(Arrays.toString(bestForest.getAllError()));
    }
}
