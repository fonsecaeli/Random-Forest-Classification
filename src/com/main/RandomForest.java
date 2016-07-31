package com.main;

import java.util.*;

class RandomForest {

    private final int ATTRIBUTE_SAMPLE_SIZE;
    private final Random randomGenerator;
    private DataSet data;
    private DecisionTree[] trees;
    private DataSet[] oobData;

    public RandomForest(DataSet data, int numTrees, double tuningFactor) {
        oobData = new DataSet[numTrees];
        int numAtts = data.getAttributes().size();
        if(detRandomSubspace(numAtts, tuningFactor) > numAtts) throw new IllegalArgumentException("Tuning Factor too large");
        this.data = data;
        randomGenerator = new Random();
        ATTRIBUTE_SAMPLE_SIZE = detRandomSubspace(data.getAttributes().size(), tuningFactor);
        growTrees(data, numTrees);
    }

    private void growTrees(DataSet data, int numTrees) {
        List<DataSet> dataSets = bootStrapData(data, numTrees);
        trees = new DecisionTree[numTrees];
        for(int i = 0; i < numTrees; i++) {
            trees[i] = new DecisionTree(dataSets.get(i), ATTRIBUTE_SAMPLE_SIZE);
        }
    }

    /*public double oob() {
        for(int i = 0; i < )
    }*/

    private int detRandomSubspace(int numberOfAttributes, double factor) {
        //can use either of the next two lines I believe
        return (int) (Math.sqrt(numberOfAttributes)*factor);
        //return (int) Math.floor(Math.log(numberOfAttributes)+1);
    }

    private List<DataSet> bootStrapData(DataSet data, int numTrees) {
        List<DataSet> dataSets = new ArrayList<DataSet>(numTrees);
        for(int i = 0; i < numTrees; i++) {
            List<Record> records = data.getRecords();
            List<Record> newRecords = new ArrayList<>();
            for(int j = 0; j < records.size(); j++) {
                newRecords.add(records.get(randomGenerator.nextInt(records.size())));
            }

            List<Record> oobRecords = data.getRecords();
            oobRecords.removeAll(newRecords);
            System.out.println(oobRecords.size());
            oobData[i] = new DataSet(data.getAttributes(), oobRecords);

            DataSet bootStrappedData = new DataSet(data.getAttributes(), newRecords);
            dataSets.add(bootStrappedData);
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
