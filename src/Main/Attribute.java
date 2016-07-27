package Main;

import java.util.List;
import java.util.ArrayList;

/**
 * Attribute stores the name of the attribute as well as the possible values of the attribute
 */
public class Attribute {
    private String name;
    private List<String> values;
    
    /**
     * Constructor, initializes name and values
     * @param n This is the name for the Attribute
     */
    public Attribute(String n){
        name=n;
        values = new ArrayList<>();
    }
    
    /**
     * Constructor, initializes name and values
     * @param n This is the name for the Attribute
     * @param v This is the list of the possible values for attribute
     */
    public Attribute(String n, List<String> v){
        name=n;
        values = new ArrayList<>();
        for(int i=0;i<v.size();i++){
            values.add(v.get(i));
        }
    }
    
    /**
     * This method adds a value to the stored list of values. 
     * Only adds if the given value is not in the list
     * @param s The value to add
     */
    public void add(String s){
        if (!hasValue(s)) 
            values.add(s);
    }
    
    /**
     * Tests if a given value is within the list
     * @param s The value to check the list against
     * @return True if the object is in the list, false otherwise
     */
    private boolean hasValue(String s){
        for (int i=0; i<values.size(); i++)
		if (s.equals(values.get(i)))
			return true;
        return false;
    }
    
    /**
     * Returns the name of the Attribute
     * @return The name of the Attribute
     */
    public String getName(){
        return name;
    }
    
    /**
     * Returns the possible values for the Attribute
     * @return The list of values.
     */
    public List<String> getValues(){
        return values;
    }

    /**
     * Returns a description of the Attribute's name and content
     * @return String of the contents
     */
    public String toString() {
        String toReturn = "";
        toReturn += getName()+": ";
        toReturn += getValues().toString();
        return toReturn;
    }
}
