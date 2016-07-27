package Main;

import Main.Attribute;
import Main.Record;
import java.util.ArrayList;
import java.util.List;

public class DataSet{
	private List <Attribute> attributes;
	private List <Record> data;
	
	public DataSet(){}
	
	public DataSet(List <Attribute> att, List <Record> da){
		data = da;
		attributes = att;
	}
	
	public List <Record> getData(){
		return data;
	}
	
	public List <Attribute> getAttributes(){
		return attributes;
	}
	
	public String toString(){
		String toReturn = "Attributes:\n";
		for (Attribute a: attributes){
			toReturn+="["+a.getName()+": ";
			toReturn+=a.getValues().toString()+"]\n";
		}
		for (Record a: data)
			for (int i=0; i<a.getData().size(); i++)
				toReturn+=a.getData().get(attributes.get(i))+"\n";
		return toReturn;
	}
}
