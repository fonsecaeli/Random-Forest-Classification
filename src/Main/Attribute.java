//package Main;

import java.util.List;
import java.util.ArrayList;

public class Attribute {
    private String name;
    private List<String> values;
    
    public Attribute(String n){
        name=n;
        values = new ArrayList<>();
    }
    
    public Attribute(String n, List<String> v){
        name=n;
        values = new ArrayList<>();
        for(int i=0;i<v.size();i++){
            values.add(v.get(i));
        }
    }
    
    public void add(String s){
        if (!hasValue(s)) 
            values.add(s);
    }
    
    private boolean hasValue(String s){
        for (int i=0; i<values.size(); i++)
		if (s.equals(values.get(i)))
			return true;
        return false;
    }
    
    public String getName(){
        return name;
    }
    
    public List<String> getValues(){
        return values;
    }
}
