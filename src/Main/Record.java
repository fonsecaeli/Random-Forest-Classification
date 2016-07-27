package Main;

import java.util.HashMap;
import java.util.LinkedHashMap;


public class Record{
    private LinkedHashMap<Attribute, String> data;
    private boolean classification; //TODO: remember set the classification at some point needs to be incorporated in ImportData.java

    public Record(){
            data = new LinkedHashMap<>();
    }

    public void add(Attribute attribute, String value){
            data.put(attribute, value);
    }

    public HashMap <Attribute, String> getData(){
            return data;
    }

    public boolean getClassification() {
		return classification;
    }

    public String getValue(Attribute att) {
            return data.get(att);
    }

    public void setClass(boolean b) {
        classification = b;
    }

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
