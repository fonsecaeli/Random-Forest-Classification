package com.main;

import java.util.*;

public class RandomForest {

    private final int ATTRIBUTE_SAMPLE_SIZE;
    private final Random randomGenerator;
    private DataSet data;
    private DecisionTree[] trees;

    public RandomForest(DataSet data, int numTrees) {
        this.data = data;
        randomGenerator = new Random();
        ATTRIBUTE_SAMPLE_SIZE = detRandomSubspace(data.getAttributes().size());
        growTrees(data, numTrees);
    }

    public void growTrees(DataSet data, int numTrees) {
        List<DataSet> dataSets = bootStrapData(data, numTrees);
        trees = new DecisionTree[numTrees];
        for(int i = 0; i < numTrees; i++) {
            trees[i] = new DecisionTree(dataSets.get(i), ATTRIBUTE_SAMPLE_SIZE);
        }
    }

    public int detRandomSubspace(int numberOfAttributes) {
        //can use either of the next two lines I believe
        //return (int) Math.sqrt(numberOfAttributes);
        return (int) Math.floor(Math.log(numberOfAttributes)+1);
    }

    public List<DataSet> bootStrapData(DataSet data, int numTrees) {
        List<DataSet> dataSets = new ArrayList<>(numTrees);
        for(int i = 0; i < numTrees; i++) {
            List<Record> records = data.getData();
            List<Record> newRecords = new ArrayList<>();
            for(int j = 0; j < records.size(); j++) {
                newRecords.add(records.get(randomGenerator.nextInt(records.size())));
            }
            DataSet d = new DataSet(data.getAttributes(), newRecords);
            dataSets.add(d);
        }
        return dataSets;
    }

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
