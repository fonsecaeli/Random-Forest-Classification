package com.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportData {

	/**
	 * This class imports data from the specified fileName path.
	 * It takes it line-by-line and splices the commas out and stores the words as strings in the appropriate classes
	 *
	 * @param fileName the path to the csv file that the data is imported from
	 * @return the data set with all the information needed
	 */
	public static DataSet importData(String fileName){
		List<Attribute> attributes = new ArrayList<>();
		ArrayList <Record> data = new ArrayList <>();
		String[] temp = {""};
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
			if ((line = br.readLine()) !=null){
				temp = line.split(",");
				for (String a: temp)
					attributes.add(new Attribute(a));
			}
			while ((line = br.readLine()) !=null){
				temp = line.split(",");
				data.add(new Record());
				for (int i=0; i<temp.length; i++){
					attributes.get(i).add(temp[i]);
					data.get(data.size()-1).add(attributes.get(i), temp[i]);
				}
			}
		} catch (IOException e){
			e.printStackTrace();
		}
        attributes = Entropy.bucketContinuousAttributes(attributes, data);
		return new DataSet(attributes, data);
	}
	
	public static DataSet importData(String fileName, List<Attribute> attributes){
		ArrayList <Record> data = new ArrayList <>();
		String[] temp = {""};
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
			if ((line = br.readLine()) !=null){
				temp = line.split(",");
				for (String s: temp){
					boolean makeSure = false;
					for (int i = 0; i<attributes.size()-1; i++) {
						if (attributes.get(i).getName().equals(s)) makeSure = true;
					}
					if (!makeSure)
						throw new Error("Unknown Attribute: ");
				}
			}
			while ((line = br.readLine()) !=null){
				temp = line.split(",");
				data.add(new Record());
				for (int i=0; i<temp.length; i++){
					attributes.get(i).add(temp[i]);
					data.get(data.size()-1).add(attributes.get(i), temp[i]);
				}
			}
		} catch (IOException e){
			e.printStackTrace();
		}
		return new DataSet(attributes, data);
	}
}
