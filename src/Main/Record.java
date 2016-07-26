package Main;

import java.util.HashMap;

public class Record{
	private HashMap <String, String> record = new HashMap <String, String>();
	
	public Record(String attribute){
		
	}
	
	public void add(String attribute, String data){
		record.put(attribute, data);
	}
	
	public HashMap <String, String> getData(){
		return record;
	}
	
	public String toString(){
		return record.toString();
	}
}
