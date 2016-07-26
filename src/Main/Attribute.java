
package Main;

import java.util.List;

public class Attribute {
    private String name;
    private List<String> values;
    
    public Attribute(String n, List<String> v){
        name=n;
        for(int i=0;i<v.size();i++){
            values.add(v.get(i));
        }
    }
    
    public boolean hasValue(String s){
        if(values.contains(s)){
            return true;
        }
        return false;
    }
    
    public List<String> getValues(){
        return values;
    }
    
    
}
