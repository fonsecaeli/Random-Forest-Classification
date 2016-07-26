//package Main;

import java.util.HashMap;


public class Record{
	private HashMap <String, String> record;
	
	public Record(){
		record = new HashMap <String, String>()
	}
	
	public HashMap <String, String> getData(){
		return record;
	}
	
	public String toString(){
		return record.toString();
	}
}
