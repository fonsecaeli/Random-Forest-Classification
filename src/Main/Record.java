//package Main;

import java.util.ArrayList;

public class Record {

	public ArrayList<String> record = new ArrayList <String>();
	
	public Record(String[] data){
		for(String a: data) record.add(a);
	}
	
	public ArrayList<String> getData(){
		return record;
	}
	
	public String toString(){
		return record.toString();
	}
}
