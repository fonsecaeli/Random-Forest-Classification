//package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ImportData {
	

	private ArrayList <Record> data;
	private ArrayList <Attribute> attributes;
	private String csvFile, line = "";
	
	public ImportData(){
		
	}
	
	public ImportData(String fileName){
		setFileName(fileName);
		importData();
	}
	
	public void setFileName(String fileName){
		csvFile = fileName;
	}
	
	public void importData(){

		data = new ArrayList <Record>();
		attributes = new ArrayList<>();
		String[] temp = {""};
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))){
			if ((line = br.readLine()) !=null)
				temp = line.split(",");
				for (String a: temp)
					attributes.add(new Attribute(a));
			
			while ((line = br.readLine()) !=null)
				temp = line.split(",");
				data.add(new Record());
				for (int i=0; i<temp.length; i++){
					attributes.get(i).add(temp[i]);
					data.get(data.size()-1).add(attributes.get(i).getName(), temp[i]);
				}
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public ArrayList <Attribute> getAttributes(){
		return attributes;
	}
	
	public ArrayList <Record> getData(){
		return data;
	}
	
	public String toString(){
		String toReturn = "";
		for (Attribute a: attributes){
			toReturn+=a.getName();
			toReturn+=a.getValues()+"\n";
		}
		for (Record a: data)
			for (int i=0; i<attributes.size(); i++)
				toReturn+=a.getData().get(attributes.get(i).getName())+"\n";
		return toReturn;
	}
}
