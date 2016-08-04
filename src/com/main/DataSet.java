package com.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataSet{

	/**
	 * The two Arraylists storing the two different types of data needed
	 */
    private List <Attribute> attributes;
    private List <Record> data;
    private String name;

	/**
	 * Takes the two arrayLists of data and stores them within the class
	 */
	public DataSet(List <Attribute> att, List <Record> da, String n){
		data = da;
		attributes = att;
                name=n;
	}

	/**
	 * @return the data arrayList of Records that stores the LinkedHashMaps
	 */
	public List <Record> getRecords(){
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
    
    public String getName(){
            return name;
	}

    /**
     * Splits a data set into a mapping of datasets that all share the same value for a common attribute
     *
     * @param data The data set to split
     * @param att The Attribute to split that data on
     * @return mapping of data sets to the values of the Attribute
     */
	public static Map<String, DataSet> splitData(DataSet data, Attribute att) {
            List<String> attValues = att.getValues();
            List<Record> records = data.getRecords();
            Map<String, DataSet> mapping = new HashMap<>();
            
            for(int i=0; i<attValues.size(); i++){
                List<Record> recordsToAdd = new ArrayList<>();
                for(int j=0; j<records.size(); j++){
                    Record r = records.get(j);
                    if(r.getValue(att).equals(attValues.get(i))){
                        recordsToAdd.add(r);
                    }
                }
                mapping.put(attValues.get(i), new DataSet(data.getAttributes(), recordsToAdd, data.getName()));
            }
            
            return mapping;
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
