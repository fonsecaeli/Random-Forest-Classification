package com.main;

import java.util.*;

public class RandomForest {

    private final int ATTRIBUTE_SAMPLE_SIZE;
    private final Random randomGenerator;
    private DataSet data;
    private DecisionTree[] trees;
    private double[] oobError;

    public RandomForest(DataSet data, int numTrees) {
        this.data = data;
        randomGenerator = new Random();
        ATTRIBUTE_SAMPLE_SIZE = detRandomSubspace(data.getAttributes().size());
        growTrees(data, numTrees);
    }

    public void growTrees(DataSet data, int numTrees) {
        int gIndex = 0;
        int oobIndex = 1;
        List<List<DataSet>> dataSets = bootStrapData(data, numTrees);
        trees = new DecisionTree[numTrees];
        oobError = new double[numTrees];
        for(int i = 0; i < numTrees; i++) {
            trees[i] = new DecisionTree(dataSets.get(i).get(gIndex), ATTRIBUTE_SAMPLE_SIZE);
            oobError[i] = trees[i].calculateError(dataSets.get(i).get(oobIndex));
        }
    }

    public double getAverageAccuracy() {
        double total = 0;
        for(int i = 0; i < oobError.length; i++) {
            total += oobError[i];
        }
        return total/oobError.length;
    }

    public double[] getTreesAccuracy() {
        return oobError;
    }

    public int detRandomSubspace(int numberOfAttributes) {
        //can use either of the next two lines I believe
        //return (int) Math.sqrt(numberOfAttributes);
        return (int) Math.floor(Math.log(numberOfAttributes)+1);
    }


    public List<List<DataSet>> bootStrapData(DataSet data, int numTrees) {
        List<List<DataSet>> dataSets = new ArrayList<List<DataSet>>(numTrees);
        for(int i = 0; i < numTrees; i++) {
            List<Record> records = data.getRecords();
            List<Record> newRecords = new ArrayList<>();
            for(int j = 0; j < records.size(); j++) {
                newRecords.add(records.get(randomGenerator.nextInt(records.size())));
            }
            //creates a data set with all the elements in data that are not included in
            //newRecords
            List<Record> oobRecords = data.getRecords();
            oobRecords.remove(newRecords);

            DataSet oobData = new DataSet(data.getAttributes(), oobRecords);
            DataSet bootStrappedData = new DataSet(data.getAttributes(), newRecords);
            List<DataSet> d = new ArrayList<>(2);
            d.add(bootStrappedData); d.add(oobData);
            dataSets.add(d);
        }
        return dataSets;
    }


    //this should work but im not sure if it is the most efficient way to do thing
    public String queryTrees(Record r) {
        String[] votes = new String[trees.length];
        for(int i = 0; i < trees.length; i++) {
            votes[i] = trees[i].query(r);
        }
        Map<String, Integer> frequencies = new HashMap<>();
        String answer = "";
        Integer bestCount = 0;
        for(String str: votes) {
            Integer count = frequencies.get(str);
            count = count != null ? count+1 : 0;
            frequencies.put(str, count);
            if(count > bestCount) {
                bestCount = count;
                answer = str;
            }
        }
        if(answer.equals("")){
            System.out.print("RandomForest has same number of votes for multiple decisions");
            //TODO: Need to find some way to handle this error not sure yet
        }

        return answer;


    }
}
