package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ImportData {
	
	private ArrayList <Record> data;
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
		try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){
			while ((line = br.readLine()) !=null)
				data.add(new Record(line.split(",")));
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public String toString(){
		String toReturn = "";
		for (Record a: data){
			toReturn+=a.toString()+"\n";
		}
		return toReturn;
	}
}
