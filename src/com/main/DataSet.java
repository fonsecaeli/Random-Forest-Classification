package com.main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataSet{
	/**
	 * The two arraylist storing the two different types of data needed
	 */
    private List <Attribute> attributes;
    private List <Record> data;

	/**
	 * Takes the two arrayLists of data and stores them within the class
	 */
	public DataSet(List <Attribute> att, List <Record> da){
		data = da;
		attributes = att;
	}

	/**
	 * @return the data arrayList of Records that stores the LinkedHashMaps
	 */
	public List <Record> getData(){
		return data;
	}

	/**
	 * @return the ArrayList with all the different attributes with all the appropriate data types
	 */
	public List <Attribute> getAttributes(){
		return attributes;
	}
        
	public Attribute getClassification(){
            return attributes.get(attributes.size()-1);
        }

	public static List<List<Record>> splitData(List<Record> data, Attribute att) {
		List<List<Record>> sortedRecords = new ArrayList<>();   //Outer List: size is number of possible values in the Attibute to test
		//Inner List: the Records which have the same value as the corresponding spot in the List of possible values

		//Sets up sortedRecords, populating it with new Lists numbering the number of possible values in the Attribute to test
		for (int i = 0; i < att.getValues().size(); i++) {
			sortedRecords.add(new ArrayList<Record>());
		}

		//Adds a Record to index i if the Record has the same value as attValues (possible values for the tested Attribute) at i
		for (int i = 0; i < att.getValues().size(); i++) {
			for (Record r : data) { //could be faster if we removed the elements from data
				if (r.getValue(att).equals(att.getValues().get(i))) { //need getValue(Attribute att) method for record, also add the .equals()
					sortedRecords.get(i).add(r);
				}
			}
		}

		return sortedRecords;
	}

	public static Map<String, DataSet> splitData(DataSet data, Attribute att) {
		Map<String, DataSet> dataSets = new LinkedHashMap<>();
		List<List<Record>> splitData = splitData(data.getData(), att);
        //System.out.println("size: "+splitData.size());
        /*for(int i=0;i<splitData.size();i++){
            for(int j=0;j<splitData.get(i).size();j++) {
                System.out.print(j+" "+splitData.get(i).size());
            }
            System.out.println();
        }*/
        for(List<Record> r: splitData) {
            if(r.size() != 0) {
                DataSet d = new DataSet(data.getAttributes(), r);
                dataSets.put(r.get(0).getValue(att), d);
            }
		}
		return dataSets;
	}
	
	/**
	 * Returns the class in String form so all the data can be seen
	 */
	public String toString(){
		String toReturn = "\nAttributes:\n";
		for (Attribute a: attributes){
			toReturn+=a.toString()+"\n";
		}
		toReturn+="\nRecords:\n";
		for (Record r: data){
			toReturn+=r.toString()+"\n";
		}
		return toReturn;
	}
}
