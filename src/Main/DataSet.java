package Main;

import java.util.List;

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
	
	/**
	 * Returns the class in String form so all the data can be seen
	 */
	public String toString(){
		String toReturn = "Attributes:\n";
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
