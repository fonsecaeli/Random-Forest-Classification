package com.main;

import java.util.*;

public class Entropy {

	/**
     * entropy of total data set
     *
     * @param set the data set to get the entropy of
     * @return the entropy of the data set
     */
	private static double entropy(DataSet set) {
            List<Record> data = set.getRecords();
            if(data.isEmpty()) {
                return 0.0;
            }

            int total = data.size();
            Attribute classificationAttribute = set.getClassification();
            double[] dataSums = new double[classificationAttribute.getValues().size()];//This will count/keep track of how many of each classification appear in the data
            for(int i = 0; i < total; i++) {
                int index = classificationAttribute.getValues().indexOf(data.get(i).getClassificationValue(set));//This is the index of the current Record's final ("classification") value within the Attribute's list
                if(index!=-1) {
                    dataSums[index]++;
                }
            }

            /**
             * handles the completely homogeneous case, because log2(0) = -Infinity but this indicates an entropy of 0
             */
            for(int i=0; i<dataSums.length; i++){
                if(dataSums[i]==0){
                    return 0;
                }
            }

            //divides all by the total, to get the proportions
            for(int i=0; i<dataSums.length; i++){
                dataSums[i] /= total;

            }

            //calculate the entropy
            double entropy=0;
            for(int i=0; i<dataSums.length; i++){
                double currentData = dataSums[i];
                entropy-=currentData*logN(currentData, dataSums.length);//Did -= instead of: sum += -1(...);
            }
            return entropy;
	}

	/**
     * Calculates the entropy of a given attribute
     * NOTE: this might not be the most efficient way to do things, complexity is pretty poor
     * @param dataSet The DataSet to check, contains the Record and Attribute Lists
     * @param att The Attribute to used to calculate entropy
     */
    private static double attributeEntropy(DataSet dataSet, Attribute att) {
        //Intializing values needed
        List<String> attValues = att.getValues();               //The list of possible values from the test Attribute
        Map<String,DataSet> dataSets = DataSet.splitData(dataSet, att);

        double attEntropy = 0.0;
        for(int i=0; i<attValues.size(); i++) {
            String currentKey = attValues.get(i);
            List<Record> currentRecords = dataSets.get(currentKey).getRecords();

            double proportion = ((double) currentRecords.size())/dataSet.getRecords().size();
            //creates a list from DataSet of the Attribute to be tested and the classification Attribute (which is placed at the end, where DataSet expects it)
            List<Attribute> attList = new ArrayList<>();
            //attList.add(att);
            attList.add(dataSet.getClassification());

            //calls entropy of a new DataSet (with an Attribute list of only the Attribute to test
            //and the classification) and does a weighted average
            DataSet ds = new DataSet(attList, currentRecords, "");
            double dataSetEntropy = entropy(ds);
            attEntropy += dataSetEntropy*proportion;
        }
        return attEntropy;
    }


    /**
     * calculates the information gain associated with splitting the data on a specific attribute
     *
     * @param data the total data set to be split
     * @param att the attribute to split on
     * @return the information gained by the split
     */
    public static double gain(DataSet data, Attribute att) {
        double entropy = entropy(data);
        double attributeEntropy = attributeEntropy(data,att);
        return entropy - attributeEntropy;
    }

    /**
     * Takes a list of Attributes and changes the continuous attributes in that list
     * into a ContinuousAttribute Object.
     *
     * @param atts the list of attributes to be examined
     * @param r the list of records that is the data from which the list of atts was
     *          extracted from
     * @return a list of ContinuousAttribute and Attribute Objects
     */
    public static List<Attribute> bucketContinuousAttributes(List<Attribute> atts, List<Record> r) {
        List<Attribute> newAtts = new ArrayList<>(atts.size());
        for(int i = 0; i < atts.size(); i++) {
            boolean flagged = false;
            boolean continuous = true;
            List<String> values = atts.get(i).getValues();
            for(int j = 0; j < values.size(); j++) {
                try {
                    double n = Double.parseDouble(values.get(j));
                }
                catch(NumberFormatException e) {
                    if(j!=0) flagged=true;
                    continuous=false;
                    break;
                }
            }
            Attribute toAdd = atts.get(i);
            if(continuous) {
                double cutOff = detCutOff(r, atts, atts.get(i));
                //System.out.println(atts.get(i).getName()+": "+cutOff);
                toAdd = new ContinuousAttribute(atts.get(i).getName(), cutOff);
            }
            else if(flagged) {
                System.out.println("They may be an error in the data under the " + atts.get(i).getName() + " column");
            }
            newAtts.add(toAdd);
        }
        return newAtts;
    }

    /**
     * Determines the cut off point between High and low for a specific continuous attribute
     * this is a helper method for bucketContinuousAttributes()
     * @param r the data
     * @param atts all the attributes from the data set
     * @param att the attribute being bucketed
     * @return the best cutoff point (using information gain)
     */
    private static double detCutOff(List<Record> r, List<Attribute> atts, Attribute att) {
        //System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        //Collections.sort(r, new AttributeSorter(att));
        double bestGain = 0.0;
        double cutOff = Double.parseDouble(r.get(r.size()/2).getValue(att));
        for(int i = 0; i < r.size(); i++) {
            List<List<Record>> highAndLow = split(r, r.get(i).getValue(att), att);
            double gain = gainForLists(r, atts, highAndLow.get(0), highAndLow.get(1));
            if(gain > bestGain) {
                bestGain = gain;
                cutOff = Double.parseDouble(r.get(i).getValue(att));
            }
        }
        //System.out.println("cutOff has been calculated");
        return cutOff;
    }

    /**
     * Splits a set of records into two buckets high and low according to some specified value
     * this is a helper method for detCutOff()
     * @param r the data
     * @param value the value to split on
     * @param att the attribute we are bucketing
     * @return the split data set
     */
    private static List<List<Record>> split(List<Record> r, String value, Attribute att) {
        List<Record> high = new ArrayList<>();
        List<Record> low = new ArrayList<>();
        double delta = .001;
        for(int i = 0; i < r.size(); i++) {
            double a = Double.parseDouble(r.get(i).getValue(att));
            double b = Double.parseDouble(value);
            if(a < b) {
                low.add(r.get(i));
            }
            else if(Math.abs(a - b) < delta) {
                low.add(r.get(i));
            }
            else {
                high.add(r.get(i));
            }
        }
        List<List<Record>> toReturn = new ArrayList<>();
        toReturn.add(high);
        toReturn.add(low);
        return toReturn;
    }

    /**
     * determines the net information gain from splitting a data set into two sections
     * helper method for bucketContinuousAttributes()
     *
     * @param data the data
     * @param atts all the attributes possible
     * @param high the high list
     * @param low the low list
     * @return the total information gain from this split
     */
    private static double gainForLists(List<Record> data, List<Attribute> atts, List<Record> high, List<Record> low) {
        double postSplit = ((double)low.size())/data.size()*entropy(new DataSet(atts, low))+((double)high.size())/data.size()*entropy(new DataSet(atts, high));
        double preSplit = entropy(new DataSet(atts, data));
        return preSplit - postSplit;
    }

    /**
     * Basic log base 2 calculation
     * @param n The number to calculate
     * @return The log base 2 of n
     */
	private static double logN(double n, double base) {
		return Math.log(n)/Math.log(base);
	}
}
