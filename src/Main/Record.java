package Main;

import java.util.HashMap;


public class Record{
	private HashMap <Attribute, String> record;
	
	public Record(){
		record = new HashMap <Attribute, String>();
	}
	
	public void add(Attribute attribute, String data){
		record.put(attribute, data);
	}
	
	public HashMap <Attribute, String> getData(){
		return record;
	}
        
        public String getAttribute(Attribute att){
            return record.get(att);
        }
}
