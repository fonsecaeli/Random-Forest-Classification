package com.main;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Entropy {

	/**
     * entropy of total data set
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
            //System.out.print("[");
            for(int i=0; i<dataSums.length; i++){
                double currentData = dataSums[i];
                //System.out.print((int)(100*dataSums[i])/100.0+" ");
                entropy-=currentData*logN(currentData, dataSums.length);//Did -= instead of: sum += -1(...);
            }
            //System.out.println("]");
            //System.out.println(entropy);
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

        //sortedRecords should be filled
        double attEntropy = 0.0;
        for(int i=0; i<attValues.size(); i++) {
            String currentKey = attValues.get(i);
            List<Record> currentRecords = dataSets.get(currentKey).getRecords();
            
            double proportion = ((double) currentRecords.size())/dataSet.getRecords().size();
            //creates a list from DataSet of the Attribute to be tested and the classification Attribute (which is placed at the end, where DataSet expects it)
            List<Attribute> attList = new ArrayList<>();
            attList.add(att);
            attList.add(dataSet.getClassification());

            //calls entropy of a new DataSet (with an Attribute list of only the Attribute to test
            //and the classification) and does a weighted average
            DataSet ds = new DataSet(attList, currentRecords);
            double dataSetEntropy = entropy(ds);
            attEntropy += dataSetEntropy*proportion;
            //System.out.print(dataSetEntropy+" ");
        }
        //System.out.println(att+" | Entropy: "+attEntropy);
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

    public List<Attribute> bucketContinuousAttributes(List<Attribute> atts, List<Record> r) {
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
            if(continuous) {
                double cutOff = detCutOff(r, atts.get(i));
                newAtts.add(new ContinuousAttribute(atts.get(i).getName(), cutOff));
            }
            else if(flagged) {
                System.out.println("They may be an error in the data under the " + atts.get(i).getName() + " column");
            }
        }
    }

    private double detCutOff(List<Record> r, Attribute att) {

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
