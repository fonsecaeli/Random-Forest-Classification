package Main;

//entropy class eli f.
//7/26/2016


import java.util.ArrayList;
import java.util.List;

public class Entropy {

	//entropy of total data set
	public static double entropy(List<Record> data) {

		if(data.size() == 0) {
			return 0.0;
		}

		double positiveP = 0.0;
		double negativeP = 0.0;
		int total = data.size();
		for(int i = 0; i < total; i++) {
			if(data.get(i).getClassification()) {
				positiveP += 1.0;
			}
			else {
				negativeP += 1.0;
			}
		}

        //handles the completely homogeneous case, because log2(0) = -Infinity but this indicates an entropy of 0
        if(positiveP == 0 || negativeP == 0) {
            return 0;
        }

		positiveP /= total;
		negativeP /= total;

		return -1*(positiveP*log2(positiveP)+negativeP*log2(negativeP));
	}

	//this might not be the most effiecent way to do things, complexity is pretty poor
	public static double attributeEntropy(List<Record> data, Attribute att) {
		List<String> attValues = att.getValues();
		List<List<Record>> sortedRecords = new ArrayList<>();
		for(int i = 0; i < attValues.size(); i++) {
			sortedRecords.add(new ArrayList<Record>());
		}
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
			double proportion = ((double) sortedRecords.get(i).size())/data.size();
			attEntropy += entropy(sortedRecords.get(i))*proportion;
		}
		return attEntropy;
	}

	private static double log2(double n) {
		return Math.log(n)/Math.log(2);
	}
}
