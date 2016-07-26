package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ImportData {
	
	private ArrayList <Record> data;
	private ArrayList <Attribute> attributes;
	private String csvFile;
	
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
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))){
			if ((line = br.readLine()) !=null)
				String[] temp = line.split(",");
				for (String a: temp)
					attributes.add(new Attribute(a));
			while ((line = br.readLine()) !=null)
				String[] temp = line.split(",");
				data.add(new Record);
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
		for (Record a: data){
			toReturn+=a.toString()+"\n";
		}
		return toReturn;
	}
}
