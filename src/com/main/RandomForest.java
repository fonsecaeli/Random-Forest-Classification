package com.main;

import java.util.*;

class RandomForest {

    private final int ATTRIBUTE_SAMPLE_SIZE;
    private final Random randomGenerator;
    private DataSet data;
    private DecisionTree[] trees;
    private List<DataSet> oobData;

    public RandomForest(DataSet data, int numTrees, double tuningFactor) {
        oobData = new ArrayList<>(numTrees);
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

    //there are error is this method right now
    public double oob() {
        int incorrect = 0;
        int correct = 0;
        List<Record> records = data.getRecords();
        for(int i = 0; i < records.size(); i++) {
            List<DecisionTree> testTrees = new ArrayList<>();
            for(int j = 0; j < oobData.size(); j++) {
                if(oobData.get(j).getRecords().contains(records.get(i))
                        && !trees[j].testRecord(records.get(i), data)) {
                    testTrees.add(trees[j]);
                }
            }
            //if we have some trees to test, test them
            if(!testTrees.isEmpty()) {
                if(validateTrees(testTrees, records.get(i))) {correct++;}
                else {incorrect++;}
            }
        }
        System.out.println(incorrect);
        System.out.println(correct);
        return (double)incorrect/(correct+incorrect);
    }

    private boolean validateTrees(List<DecisionTree> trees, Record r) {
        int correct = 0;
        for(DecisionTree t: trees) {
            if(t.testRecord(r, data)) {
                correct++;
            }
        }
        if((double)correct/trees.size() > .5) {
            return true;
        }
        return false;
    }

    private int detRandomSubspace(int numberOfAttributes, double factor) {
        //can use either of the next two lines I believe
        return (int) (Math.sqrt(numberOfAttributes)*factor);
        //return (int) Math.floor(Math.log(numberOfAttributes)+1);
    }

    private List<DataSet> bootStrapData(DataSet data, int numTrees) {
        List<DataSet> dataSets = new ArrayList<>(numTrees);
        for(int i = 0; i < numTrees; i++) {
            List<Record> records = data.getRecords();
            List<Record> newRecords = new ArrayList<>();
            for(int j = 0; j < records.size(); j++) {
                newRecords.add(records.get(randomGenerator.nextInt(records.size())));
            }

            //makes a list of all the excluded records from each boot strapped data set
            List<Record> oobRecords = new ArrayList<>(data.getRecords());
            oobRecords.removeAll(newRecords);
            oobData.add(new DataSet(data.getAttributes(), oobRecords));

            DataSet bootStrappedData = new DataSet(data.getAttributes(), newRecords);
            dataSets.add(bootStrappedData);
        }
        return dataSets;
    }


    //this should work but im not sure if it is the most efficient way to do thing
    public String queryTrees(Record r, DecisionTree[] trees) {
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
