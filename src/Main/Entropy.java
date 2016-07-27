package Main;

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
            double[] dataSums = new double[total];//This will count/keep track of how many of each classification appear in the data
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
                entropy-=currentData*log2(currentData);//Did -= instead of: sum += -1(...);
            }
            return entropy;
	}

	/**
         * Calculates the entropy of a given attribute
         * NOTE: this might not be the most efficient way to do things, complexity is pretty poor
         * @param dataSet The DataSet to check, contains the Record and Attribute Lists
         * @param index The index of the Attribute to test within dataSet.getAttributes();
         */ 
	
        public static double attributeEntropy(DataSet dataSet, int index) {
                //Intializing values needed
                List<Record> data=dataSet.getData();                    //The list of records from with dataSet
                Attribute att = dataSet.getAttributes().get(index);     //The attribute which will be tested
		List<String> attValues = att.getValues();               //The list of possible values from the test Attribute
		List<List<Record>> sortedRecords = new ArrayList<>();   //Outer List: size is number of possible values in the Attibute to test
                                                                        //Inner List: the Records which have the same value as the corresponding spot in the List of possible values 
                
                //Sets up sortedRecords, populating it with new Lists numbering the number of possible values in the Attribute to test
		for(int i = 0; i < attValues.size(); i++) {
			sortedRecords.add(new ArrayList<Record>());
		}
                
                //Adds a Record to index i if the Record has the same value as attValues (possible values for the tested Attribute) at i
		for(int i = 0; i < attValues.size(); i++) {
			for(Record r: data) { //could be faster if we removed the elements from data
				if(r.getValue(att).equals(attValues.get(i))) { //need getValue(Attribute att) method for record, also add the .equals()
					sortedRecords.get(i).add(r);
				}
			}
		}
                
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
                        DataSet ds = new DataSet(attList, data);
			attEntropy += entropy(ds)*proportion;
		}
                
		return attEntropy;
	}

        /**
         * Basic log base 2 calculation
         * @param n The number to calculate
         * @return The log base 2 of n
         */
	private static double log2(double n) {
		return Math.log(n)/Math.log(2);
	}
}
