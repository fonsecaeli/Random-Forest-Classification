package com.main;

import java.util.ArrayList;
import java.util.List;

public class Entropy {

	/**
         * entropy of total data set
         */ 
	public static double entropy(DataSet set) {
            List<Record> data = set.getData();
            if(data.size() == 0) {
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
     * @param attr The Attribute to used to calculate entropy
     */
	
    public static double attributeEntropy(DataSet dataSet, Attribute attr) {
        //Intializing values needed
        List<Record> data=dataSet.getData();                    //The list of records from with dataSet
        Attribute att = attr;     //The attribute which will be tested
        List<String> attValues = att.getValues();               //The list of possible values from the test Attribute
        List<List<Record>> sortedRecords = DataSet.splitData(data, att);

        //sortedRecords should be filled
        double attEntropy = 0.0;
        for(int i = 0; i < attValues.size(); i++) {
            double proportion = ((double) sortedRecords.get(i).size())/data.size();//the ratio of how many had a certain Attribute value over the whole

            //creates a list from DataSet of the Attribute to be tested and the classification Attribute (which is placed at the end, where DataSet expects it)
            List<Attribute> attList = new ArrayList<>();
            attList.add(att);
            attList.add(dataSet.getClassification());

            //calls entropy of a new DataSet (with an Attribute list of only the Attribute to test
            //and the classification) and does a weighted average
            DataSet ds = new DataSet(attList, sortedRecords.get(i));
            attEntropy += entropy(ds)*proportion;
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
        return entropy(data) - attributeEntropy(data, att);
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
