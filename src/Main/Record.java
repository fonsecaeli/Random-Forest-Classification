//package Main;

import java.util.HashMap;


public class Record{
	private HashMap <String, String> record = new HashMap <String, String>();
	
	public Record(String attribute){
		
	}
	
	public HashMap <String, String> getData(){
		return record;
	}
	
	public String toString(){
		return record.toString();
	}
}
