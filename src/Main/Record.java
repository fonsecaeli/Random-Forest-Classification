package Main;

import java.util.HashMap;
import java.util.LinkedHashMap;


public class Record{
	/**
	* Stores the data in a LinkedHashMap with the keys as the data types and the values as the actual values
	*/
    private LinkedHashMap<Attribute, String> data;
    
	/**
	* Initializes data
	*/
    public Record(){
            data = new LinkedHashMap<>();
    }

	/**
	* adds an entry to the hashmap
	*/
    public void add(Attribute attribute, String value){
            data.put(attribute, value);
    }
	
	/**
	* Returns the hashmap so data can be retrieved from it
	*/
    public HashMap <Attribute, String> getData(){
            return data;
    }

	/**
	* Input key, get value
	*/
    public String getValue(Attribute att) {
            return data.get(att);
    }

	/**
	* Returns the string representation of the class
	*/
    public String toString() {
        String str = "";
        for(Attribute att: data.keySet()) {
            String key = att.toString();
            String value = data.get(att);
            str += key + ": " + value + ", ";
        }
        //System.out.println(data.keySet());
        return str;
    }
}
