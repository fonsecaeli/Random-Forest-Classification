package com.main;

import java.util.*;

public class RandomForest {

    private final int ATTRIBUTE_SAMPLE_SIZE;
    private final Random randomGenerator;
    private DataSet data;
    private DecisionTree[] trees;
    private List<DataSet> oobData;

    /**
     * Constructor for a random forest
     *
     * @param data the data set used to grow the trees in this forest
     * @param numTrees the number of trees in the forest
     * @param tuningFactor a factor used to determine the size of the random subspace of Attributes used to grow each tree
     */
    public RandomForest(DataSet data, int numTrees, double tuningFactor) {
        oobData = new ArrayList<>(numTrees);
        int numAtts = data.getAttributes().size();
        if(detRandomSubspace(numAtts, tuningFactor) > numAtts) throw new IllegalArgumentException("Tuning Factor too large");
        this.data = data;
        randomGenerator = new Random();
        ATTRIBUTE_SAMPLE_SIZE = detRandomSubspace(data.getAttributes().size(), tuningFactor);
        growTrees(numTrees);
    }

    /**
     * grows each tree in the forest according to a combination of the ID3 and C4.5
     * algorithms, both developed by Ross Quinlan.  Trees are save into an array of
     * trees which this Object stores for later use.  Trees are grown according to the
     * data set that was specified during construction
     *
     * @param numTrees the number of trees that should be grown.
     */
    private void growTrees(int numTrees) {
        List<DataSet> dataSets = bootStrapData(data, numTrees);
        trees = new DecisionTree[numTrees];
        for(int i = 0; i < numTrees; i++) {
            trees[i] = new DecisionTree(dataSets.get(i), ATTRIBUTE_SAMPLE_SIZE);
        }
    }

    /**
     * Calculates the out of bag error (oob error) for this random forest.
     * The oob error finds pieces of data that where not used to train different
     * groups of trees and then tests those trees using the data.  This works
     * as a bench mark for determining the error rate of this forest because no
     * data that was used to grow trees will be used for testing.  oob error has
     * been empirically shown to be equivalent to error estimates from using new
     * training data sets.
     *
     * @return the oob error rate for this random forest
     */
    public double oob() {
        int incorrect = 0;
        int correct = 0;
        List<Record> records = data.getRecords();
        for(int i = 0; i < records.size(); i++) {
            List<DecisionTree> testTrees = new ArrayList<>();
            for(int j = 0; j < oobData.size(); j++) {
                if(oobData.get(j).getRecords().contains(records.get(i))) {
                    testTrees.add(trees[j]);
                }
            }
            //if we have some trees to test, test them
            if(!testTrees.isEmpty()) {
                if(validateTrees(testTrees, records.get(i))) {correct++;}
                else {incorrect++;}
            }
        }
        System.out.println("incorrect: "+incorrect);
        System.out.println("correct: "+correct);
        return (double)incorrect/(correct+incorrect);
    }

    /**
     * Helper method for calculating the oob error of this forest. Determines
     * if a group of trees or a sub forest correctly classified a specific data
     * point.
     *
     * @param trees the sub forest to be tested
     * @param r the data point the trees are to be tested against
     * @return true indicates a correct classification, false indicated an incorrect classification
     */
    private boolean validateTrees(List<DecisionTree> trees, Record r) {
        int correct = 0;
        for(DecisionTree t: trees) {
            if(t.testRecord(r, data)) {
                correct++;
            }
        }
        if(correct > (trees.size()-correct)) {
            return true;
        }
        return false;
    }

    /**
     * Determines the number of attributes to be randomly selected out the pool
     * of all attributes each time a tree in the forest splits on a data set.  After
     * using the number the trees then find the best split among this random subspace of
     * Attributes.  This random factor gives each tree a different architectures thus improving
     * how this forest generalizes to new test cases.
     *
     * @param numberOfAttributes the number of possible attributes
     * @param factor a tuning factor, sometimes we need to tune the number
     * of attributes to select from because itcan cause the forest to perform better or worse
     * @return the size of the random subspace
     */
    private int detRandomSubspace(int numberOfAttributes, double factor) {
        //can use either of the next two lines I believe
        return (int) (Math.sqrt(numberOfAttributes)*factor);
        //return (int) Math.floor(Math.log(numberOfAttributes)+1);
    }

    /**'
     * Takes the data the forest should be modeling and splits it up into n different
     * data sets.  Random data points are taken with replacement from the original data
     * set to create the new data sets.  Then these data sets are used to grow each
     * tree in the forest.  n = number of trees in the forest.
     *
     * @param dataToSample the data set to split up
     * @param numTrees the number of trees in the forest
     * @return a list of new data sets used to grow the trees in this forest
     */
    private List<DataSet> bootStrapData(DataSet dataToSample, int numTrees) {
        List<DataSet> dataSets = new ArrayList<>(numTrees);
        for(int i = 0; i < numTrees; i++) {
            List<Record> records = dataToSample.getRecords();
            List<Record> newRecords = new ArrayList<>();
            for(int j = 0; j < records.size(); j++) {
                newRecords.add(records.get(randomGenerator.nextInt(records.size())));
            }

            //makes a list of all the excluded records from each boot strapped data set
            List<Record> oobRecords = new ArrayList<>(dataToSample.getRecords());
            oobRecords.removeAll(newRecords);
            oobData.add(new DataSet(dataToSample.getAttributes(), oobRecords));

            DataSet bootStrappedData = new DataSet(data.getAttributes(), newRecords);
            dataSets.add(bootStrappedData);
        }
        return dataSets;
    }

    /**
     * Determines the classification of an unclassified data point but running
     * it down each tree in the forest.  All the result from this process are stored
     * and then the class with the most "votes" overall from all trees is assigned
     * as the classification for this data point.
     *
     * @param r the data point to be classified
     * @param trees the array of trees used to classify this data point
     * @return the classification for this data point
     */
    public String queryTrees(Record r, DecisionTree[] trees) {
        String[] votes = new String[trees.length];
        for(int i = 0; i < trees.length; i++) {
            votes[i] = trees[i].query(r);
        }
        Map<String, Integer> frequencies = new HashMap<>();
        String answer = "";
        Integer bestCount = 0;
        for(String str: votes) {
            if(!str.equals(DecisionTree.ERROR_MESSAGE)) {
                Integer count = frequencies.get(str);
                count = count != null ? count + 1 : 0;
                frequencies.put(str, count);
                if (count > bestCount) {
                    bestCount = count;
                    answer = str;
                }
            }
        }
        if(answer.equals("")){
            System.out.print("RandomForest has same number of votes for multiple decisions");
            //TODO: Need to find some way to handle this error not sure yet
        }

        return answer;
    }

    @Override
    /**
     * Creates a String representation of this Object.  Basically returns a string
     * representing each tree in the forest
     *
     * @return String representation of the Object
     */
    public String toString() {
        String toReturn = "";
        for(DecisionTree t: trees) {
            toReturn += t.toString()+"\n";
        }
        return toReturn;
    }
}
