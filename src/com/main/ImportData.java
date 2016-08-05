package com.main;

import java.io.BufferedReader;
import java.io.File;
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
		ArrayList <Attribute> attributes = new ArrayList<>();
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
        //buckets the continuous attributes
        return configAttributes(attributes, data, fileName);
	}

    public static DataSet configAttributes(List<Attribute> attributes, List<Record> data, String fileName) {
        List<Attribute> bucketedAttributes = Entropy.bucketContinuousAttributes(attributes, data);
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < attributes.size(); j++) {
                try {
                    String str = data.get(i).getValue(bucketedAttributes.get(j));
                } catch (NullPointerException e) {
                    Record r = data.get(i);
                    r.add(bucketedAttributes.get(j), r.getValue(attributes.get(j)));
                    r.remove(attributes.get(j));
                }
            }
        }
        return new DataSet(bucketedAttributes, data, fileName.substring((int) Math.max(fileName.lastIndexOf("\\"), fileName.lastIndexOf("/")) + 1, fileName.length() - 4));
    }

	public static DataSet importData(File file){
		ArrayList <Attribute> attributes = new ArrayList<>();
		ArrayList <Record> data = new ArrayList <>();
		String[] temp = {""};
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
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
        String fileName = file.getPath();
        return configAttributes(attributes, data, fileName);
	}

	public static DataSet importData(String fileName, ArrayList <Attribute> attributes){
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
        return configAttributes(attributes, data, fileName);
	}

	public static DataSet importData(File file, ArrayList <Attribute> attributes){
		ArrayList <Record> data = new ArrayList <>();
		String[] temp = {""};
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
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
        String fileName = file.getPath();
        return configAttributes(attributes, data, fileName);
	}
}
